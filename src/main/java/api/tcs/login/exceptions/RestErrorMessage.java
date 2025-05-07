package api.tcs.login.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestErrorMessage {
    
    private HttpStatus erro;
    private String mensagem;
}
