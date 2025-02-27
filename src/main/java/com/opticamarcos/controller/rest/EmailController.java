package com.opticamarcos.controller.rest;

import com.opticamarcos.exceptions.CustomException;
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
    public @ResponseBody void enviarEmail(@RequestParam Integer numeroFicha) throws MessagingException, CustomException {
        emailService.sendMailWithAttachment(numeroFicha);
    }

}
