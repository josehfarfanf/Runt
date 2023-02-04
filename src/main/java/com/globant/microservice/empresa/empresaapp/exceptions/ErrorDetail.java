package com.globant.microservice.empresa.empresaapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetail {

    private Date date;

    private String message;

    private String details;
}
