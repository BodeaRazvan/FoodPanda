package com.example.foodpanda.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailHandler {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String email, String subject, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("bodeatest@gmail.com");
        mail.setTo(email);
        mail.setText(message);
        mail.setSubject(subject);
        //System.out.println(1);
        mailSender.send(mail);
        //System.out.println(2);
        System.out.println("Mail sent");
    }
}
