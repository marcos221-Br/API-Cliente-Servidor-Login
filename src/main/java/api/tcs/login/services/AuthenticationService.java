package api.tcs.login.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import api.tcs.login.exceptions.UnauthorizedException;
import api.tcs.login.models.Usuario;
import api.tcs.login.repositories.UsuarioRepository;

@Service
public class AuthenticationService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Usuario authenticate(Usuario usuario){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword()));
            return usuarioRepository.findByEmail(usuario.getUsername()).orElseThrow();
        }catch(AuthenticationException e){
            throw new UnauthorizedException("Credenciais Incorretas");
        }
    }
}
