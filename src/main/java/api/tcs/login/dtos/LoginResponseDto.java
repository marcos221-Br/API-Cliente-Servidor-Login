package api.tcs.login.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponseDto {
    
    private String token;

    public static LoginResponseDto loginDto(String token){
        return new LoginResponseDto(token);
    }
}
