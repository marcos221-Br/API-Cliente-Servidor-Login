package api.tcs.login.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(DuplicateEntryException.class)
    private ResponseEntity<RestErrorMessage> duplicateEntryHandler(DuplicateEntryException exception){
        RestErrorMessage response = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(UnauthorizedException.class)
    private ResponseEntity<RestErrorMessage> unauthorizedHandler(UnauthorizedException exception){
        RestErrorMessage response = new RestErrorMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<RestErrorMessage> notFoundHandler(NotFoundException exception){
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DefaultException.class)
    private ResponseEntity<RestErrorMessage> defaultHandler(DefaultException exception){
        RestErrorMessage response = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
