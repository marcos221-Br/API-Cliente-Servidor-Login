package api.tcs.login.dtos.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioDto {
    
    private Integer id;
    @NotBlank
    private String nome;
    private String email;
    @NotBlank
    private String senha;
}
