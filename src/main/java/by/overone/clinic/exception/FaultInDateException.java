package by.overone.clinic.exception;

public class FaultInDateException extends RuntimeException {
    private String errorCode;

    public FaultInDateException(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
