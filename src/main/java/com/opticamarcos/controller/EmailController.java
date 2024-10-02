package com.opticamarcos.controller;

import com.opticamarcos.exceptions.ObjectNotFoundException;
import com.opticamarcos.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mailing")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    @Operation(summary = "Envia e-mail de la ficha",
            description = "Se pasas por parametro el numero de ficha a enviar",
            tags = {"Mailing"}
    )
    public @ResponseBody void enviarEmail(@RequestParam Integer numeroFicha) throws MessagingException, ObjectNotFoundException {
        emailService.sendMailWithAttachment(numeroFicha);
    }

}
