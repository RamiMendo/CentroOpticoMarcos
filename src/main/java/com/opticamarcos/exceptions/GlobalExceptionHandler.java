package com.opticamarcos.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//RENDERIZA LA EXCEPCION DE LA FORMA QUE YO QUIERO A PARTIR DE LO QUE PONGO COMO MENSAJE.
    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<Object> handleFichaNotFound(CustomException e, WebRequest request) {
        String error = e.getMessage();
        return handleExceptionInternal(e, error, new HttpHeaders(), e.getHttpStatus(), request);
    }

    @ExceptionHandler(value = {FileNotFoundException.class})
    public ResponseEntity<Object> handleFileNotFound(FileNotFoundException e, WebRequest request) {
        String error = e.getMessage();
        return handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
