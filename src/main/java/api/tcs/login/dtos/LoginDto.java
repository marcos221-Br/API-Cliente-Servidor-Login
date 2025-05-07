package api.tcs.login.dtos;

import api.tcs.login.models.Usuario;
import lombok.Data;

@Data
public class LoginDto {

    private String email;
    private String senha;

    public Usuario usuarioObject(){
        return new Usuario(email, senha);
    }
}
