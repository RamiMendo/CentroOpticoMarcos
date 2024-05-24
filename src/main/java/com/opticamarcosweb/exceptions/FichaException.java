package com.opticamarcosweb.exceptions;

import org.springframework.http.HttpStatus;

public class FichaException extends Exception{

    private String message;
    private HttpStatus httpStatus;

    public FichaException(String message, String message1, HttpStatus httpStatus) {
        super(message);
        this.message = message1;
        this.httpStatus = httpStatus;
    }
}
