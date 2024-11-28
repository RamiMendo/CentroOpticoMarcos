package com.opticamarcos.component;

import com.opticamarcos.exceptions.CustomException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {

    @Around("execution(* com.opticamarcos.service.*.findById(*))")
    public Object findById(ProceedingJoinPoint joinPoint) throws Throwable {
        Object args = joinPoint.getArgs()[0];

        if(Objects.equals(args.toString(), "0"))
            throw new CustomException("ID MAL INGRESADO!\nDEBE SER MAYOR A CERO.", HttpStatus.BAD_REQUEST);

        Object response = joinPoint.proceed();
        if (response == null){
            throw new CustomException("NO EXISTE EN BASE DE DATOS!", HttpStatus.NOT_FOUND);
        }

        return response;
    }
}
