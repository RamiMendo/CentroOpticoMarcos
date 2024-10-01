package com.opticamarcos.exceptions;

import org.springframework.http.HttpStatus;

public class ObjectNotFoundException extends CustomException{

    public ObjectNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
