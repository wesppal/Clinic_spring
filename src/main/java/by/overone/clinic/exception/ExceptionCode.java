package by.overone.clinic.exception;

public enum ExceptionCode {
    NOT_EXISTING_USER ("40"),
    NOT_EXISTING_DOCTOR ("41"),
    NOT_EXISTING_PET("42"),
    NOT_EXISTING_PETS_BY_USER("43"),
    NOT_EXISTING_USER_WITH_NAME("44"),
    NOT_MISMATCH_USER_ID("31");

    private final String errorCode;

    ExceptionCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
