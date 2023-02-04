package com.globant.microservice.empresa.empresaapp.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetail> handleAllException(Exception ex, WebRequest request){
        ErrorDetail errorDetail=new ErrorDetail(new Date(), ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CompaniaNotFoundException.class)
    public final ResponseEntity<ErrorDetail> handleCompaniNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetail errorDetails = new ErrorDetail(new Date(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetail>(errorDetails, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(DepartamentoNotFoundException.class)
    public final ResponseEntity<ErrorDetail> handleDepartamentoNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetail errorDetails = new ErrorDetail(new Date(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetail>(errorDetails, HttpStatus.NOT_FOUND);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String message="";
        List<FieldError> errors = ex.getFieldErrors();
        for (FieldError err:errors){
            message+="campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage().concat(", "));
        }
        ErrorDetail errorDetails = new ErrorDetail(new Date(),
                message, request.getDescription(false));



        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
