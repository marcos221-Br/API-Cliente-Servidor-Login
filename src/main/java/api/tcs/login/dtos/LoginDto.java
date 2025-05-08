package api.tcs.login.dtos;

import api.tcs.login.models.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String senha;

    public Usuario usuarioObject(){
        return new Usuario(email, senha);
    }
}
