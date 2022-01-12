package by.overone.clinic.exception;

public class EntityNotFoundException extends RuntimeException{
    private int errorCode;

    public EntityNotFoundException(String errorCode) {
        super(errorCode);
    }
}
