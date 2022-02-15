package by.overone.clinic.exception;

public class EntityNotFoundException extends RuntimeException {
    private String errorCode;

    public EntityNotFoundException(String errorCode) {
        super(errorCode);
    }
}
