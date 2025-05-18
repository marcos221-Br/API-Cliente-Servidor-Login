package api.tcs.login.dtos.models;

import lombok.Data;

@Data
public class EmailDto {

    private Integer emailId;
    private String assunto;
    private String emailRemetente;
    private String emailDestinatario;
    private String corpo;
    private String status;
    private String dataEnvio;
}
