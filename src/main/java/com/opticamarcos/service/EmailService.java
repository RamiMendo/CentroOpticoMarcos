package com.opticamarcos.service;

import com.opticamarcos.exceptions.CustomException;
import com.opticamarcos.model.entity.Ficha;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {

    @Autowired
    private FichaService fichaService;

    private JavaMailSender mailSender;
    private SimpleMailMessage mailMessage;

    public EmailService(JavaMailSender mailSender, SimpleMailMessage mailMessage) {
        this.mailSender = mailSender;
        this.mailMessage = mailMessage;
    }

    public void sendMailWithAttachment(Integer numeroFicha) throws MessagingException, CustomException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        Ficha ficha = fichaService.findById(numeroFicha);

        String subject = "FICHA OPTICA Nº " + ficha.getIdFicha() + " - " + ficha.getCliente().getNombre();
        String texto = mailMessage.getText() + ficha.getIdFicha();
        String pathFicha = "C:\\RAMIRO\\Programacion\\Java\\Proyectos\\CentroOpticoMarcosWeb\\src\\main\\resources\\fichas\\Ficha-" + ficha.getIdFicha() + ".pdf";

        //mimeMessageHelper.setTo("centroopticomarcos@gmail.com");
        mimeMessageHelper.setTo("ramamendoza@gmail.com");
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(texto);

        FileSystemResource file = new FileSystemResource(new File(pathFicha));
        mimeMessageHelper.addAttachment(subject + ".pdf", file);

        mailSender.send(mimeMessage);
    }

}
