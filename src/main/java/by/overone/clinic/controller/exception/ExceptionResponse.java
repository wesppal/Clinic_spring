package by.overone.clinic.controller.exception;

import lombok.Data;

@Data
public class ExceptionResponse{

private String message;

    public ExceptionResponse(String message) {
        this.message = message;
    }
}
