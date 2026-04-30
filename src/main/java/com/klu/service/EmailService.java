package com.klu.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendOtpMail(String toEmail, String otp) throws RuntimeException {
        try {
            log.info("Sending OTP email to: {}", toEmail);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(toEmail);
            helper.setSubject("Your OTP Code");
            helper.setText("Your OTP is: " + otp + ". It is valid for 5 minutes.");

            mailSender.send(message);
            log.info("OTP email sent successfully to: {}", toEmail);
        } catch (MessagingException e) {
            log.error("Failed to send OTP email to: {}. Error: {}", toEmail, e.getMessage());
            throw new RuntimeException("Failed to send OTP email. Please check email configuration.");
        } catch (Exception e) {
            log.error("Unexpected error sending OTP email to: {}. Error: {}", toEmail, e.getMessage());
            throw new RuntimeException("Unexpected error sending OTP email.");
        }
    }
}
