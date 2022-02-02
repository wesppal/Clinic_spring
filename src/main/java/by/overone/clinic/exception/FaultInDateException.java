package by.overone.clinic.exception;

public class FaultInDateException extends RuntimeException {
    private int errorCode;

    public FaultInDateException(String errorCode) {
        super(errorCode);
    }
}
