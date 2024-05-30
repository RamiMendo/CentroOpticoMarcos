package com.opticamarcosweb.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class EntidadException extends Exception{

    private String message;
    private HttpStatus httpStatus;

    public EntidadException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
