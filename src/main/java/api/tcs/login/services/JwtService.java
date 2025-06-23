package api.tcs.login.services;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    @Value("${security.jwt.secret-key}")
    private String key;

    @Value("${security.jwt.expiration-time}")
    private long expirationTime;

    private final StringRedisTemplate redisTemplate;

    public JwtService(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public void whitelistToken(String token){
        redisTemplate.opsForValue().set(token, "whitelisted", expirationTime, TimeUnit.MILLISECONDS);
    }

    public void removeWhitelistToken(String token){
        redisTemplate.opsForValue().getAndDelete(token);
    }

    public boolean isTokenWhitelisted(String token){
        return redisTemplate.hasKey(token);
    }

    private Key getSignInKey() {
        byte[] key = Decoders.BASE64.decode(this.key);
        return Keys.hmacShaKeyFor(key);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + expiration)).signWith(getSignInKey(), SignatureAlgorithm.HS512).compact();
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, expirationTime);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && isTokenWhitelisted(token);
    }

    public long getExpirationTime(){
        return this.expirationTime;
    }

    public List<String> getLoggedUsers(){
        Set<String> allKeys = redisTemplate.keys("*");
        List<String> filtredKeys = new ArrayList<>();

        try{
            for(String key : allKeys){
                if(!key.startsWith("pcp:")){
                    filtredKeys.add(extractUsername(key));
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }

        return filtredKeys;
    }
}
