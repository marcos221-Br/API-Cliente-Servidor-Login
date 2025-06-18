package api.tcs.login.dtos.models;

import lombok.Data;

@Data
public class RascunhoDto {
    
    private String assunto;
    private String emailDestinatario;
    private String corpo;
}
