package by.overone.clinic.controller.exception;

import by.overone.clinic.exception.EntityNotFoundException;
import by.overone.clinic.exception.FaultInDateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        String message = messageSource.getMessage("00001", null, request.getLocale());
        ExceptionResponse response = new ExceptionResponse();
        response.setException(ex.getClass().getSimpleName());
        response.setMessage(message);
        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status,
                                                                         WebRequest request) {
        String errorCode = "00002";
        String message = messageSource.getMessage(errorCode, null, request.getLocale());
        ExceptionResponse response = new ExceptionResponse();
        response.setException(ex.getClass().getSimpleName());
        response.setErrorCode(errorCode);
        response.setMessage(message);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<ExceptionResponse> list = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ExceptionResponse(error.getField() + " " + error.getDefaultMessage(),
                        null, null))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        log.info(ex.getMessage());
        String errorCode = "00003";
        String message = messageSource.getMessage(errorCode, null, request.getLocale());
        ExceptionResponse response = new ExceptionResponse();
        response.setException(ex.getClass().getSimpleName());
        response.setMessage(message);
        response.setErrorCode(errorCode);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> entityNotFoundHandler(EntityNotFoundException e) {
        log.error("Not found exception: ", e);
        ExceptionResponse response = new ExceptionResponse();
        response.setException(e.getClass().getSimpleName());
        response.setErrorCode(e.getMessage());
        String message = "";
        switch (e.getMessage()) {
            case "31":
                message = "User ID error.";
                break;
            case "40":
                message = "User not found.";
                break;
            case "41":
                message = "Doctor not found";
                break;
            case "42":
                message = "Pet not found.";
                break;
            case "43":
                message = "This user don't have pets.";
                break;
            case "44":
                message = "User was not found for your request.";
                break;
        }
        response.setMessage(message);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionResponse> sqlExceptionHandler(SQLException e) {
        log.error("SQL exception: ", e);
        ExceptionResponse response = new ExceptionResponse();
        response.setException(e.getClass().getSimpleName());
        response.setErrorCode("51");
        response.setMessage("BD exception. " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> sqlDuplicateHandler(SQLIntegrityConstraintViolationException e) {
        log.error("SQL duplicate exception: ", e);
        ExceptionResponse response = new ExceptionResponse();
        response.setException(e.getClass().getSimpleName());
        response.setErrorCode("51");
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(FaultInDateException.class)
    public ResponseEntity<ExceptionResponse> dateFaultHandler(FaultInDateException e) {
        log.error("Not found exception: ", e);
        ExceptionResponse response = new ExceptionResponse();
        response.setException(e.getClass().getSimpleName());
        response.setErrorCode(e.getMessage());
        String message = "";
        switch (e.getMessage()) {
            case "3001":
                message = "Hours in admission_date must be more than or equal 8 and less than 17.";
                break;
            case "3002":
                message = "Minutes in admission_date must be equal to 0 or 30.";
                break;
            case "3003":
                message = "This time is already being used.";
                break;

        }
        response.setMessage(message);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
