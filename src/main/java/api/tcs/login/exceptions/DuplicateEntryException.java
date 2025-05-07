package api.tcs.login.exceptions;

public class DuplicateEntryException extends RuntimeException{
    
    public DuplicateEntryException(String message){
        super(message);
    }
}
