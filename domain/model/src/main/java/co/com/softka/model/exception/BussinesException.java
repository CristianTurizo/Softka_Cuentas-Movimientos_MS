package co.com.softka.model.exception;

import lombok.Getter;

@Getter
public class BussinesException extends RuntimeException {

    private final Message errorMessage;

    public BussinesException(Message errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
