package api.tcs.login.dtos.models;

import lombok.Data;

@Data
public class UsuarioDto {
    
    private Integer id;
    private String nome;
    private String email;
    private String senha;
}
