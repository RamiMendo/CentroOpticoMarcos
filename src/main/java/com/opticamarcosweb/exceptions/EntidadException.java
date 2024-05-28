package com.opticamarcosweb.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class FichaException extends Exception{

    private String message;
    private HttpStatus httpStatus;

    public FichaException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
