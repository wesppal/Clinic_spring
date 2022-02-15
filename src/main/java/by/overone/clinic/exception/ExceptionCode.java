package by.overone.clinic.exception;

public enum ExceptionCode {
    NOT_EXISTING_USER("004"),
    NOT_EXISTING_DOCTOR("005"),
    NOT_EXISTING_PET("006"),
    NOT_EXISTING_PETS_BY_USER("007"),
    NOT_EXISTING_USER_WITH_NAME("008"),
    NOT_MISMATCH_HOURS_IN_RECORD("009"),
    NOT_MISMATCH_MINUTES_IN_RECORD("010"),
    ALREADY_EXISTING_RECORD("011");


    private final String errorCode;

    ExceptionCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
