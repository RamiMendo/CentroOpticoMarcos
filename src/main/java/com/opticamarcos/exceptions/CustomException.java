package com.opticamarcos.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends Exception{

    private String message;
    private HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
