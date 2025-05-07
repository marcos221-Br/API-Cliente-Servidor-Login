package api.tcs.login.dtos;

import api.tcs.login.dtos.models.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioResponseDto {
    
    private String mensagem;
    private UsuarioDto usuario;
}
