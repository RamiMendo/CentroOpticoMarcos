package com.opticamarcos.exceptions;

import org.springframework.http.HttpStatus;

public class BadInputException extends CustomException{

    public BadInputException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
