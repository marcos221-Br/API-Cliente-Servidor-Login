package api.tcs.login.exceptions;

public class UnauthorizedException extends RuntimeException{
    
    public UnauthorizedException(String message){
        super(message);
    }
}
