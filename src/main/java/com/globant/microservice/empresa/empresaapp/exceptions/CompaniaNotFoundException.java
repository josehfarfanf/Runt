package com.globant.microservice.empresa.empresaapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CompaniaNotFoundException extends RuntimeException{

    public CompaniaNotFoundException(String message){
        super(message.concat(" no existe"));
    }
}
