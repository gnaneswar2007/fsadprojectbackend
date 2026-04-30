package com.klu.service;

import com.klu.exception.InvalidOtpException;
import com.klu.model.OtpToken;
import com.klu.repository.OtpTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

// @Service - Disabled since OTP service is no longer used
public class OtpService {

    private final OtpTokenRepository otpTokenRepository;
    private final EmailService emailService;
  

    @Value("${app.otp.expiration-minutes}")
    private long expirationMinutes;

    @Value("${app.otp.max-attempts}")
    private int maxAttempts;

    public OtpService(OtpTokenRepository otpTokenRepository, EmailService emailService) {
        this.otpTokenRepository = otpTokenRepository;
        this.emailService = emailService;
    }

    public void generateAndSendOtp(String email, String purpose) {
    	String otp = String.valueOf((int)(Math.random() * 9000) + 1000);

        OtpToken token = new OtpToken();
        token.setEmail(email);
        token.setOtpCode(otp);
        token.setPurpose(purpose);
       
        token.setAttempts(0);
        token.setCreatedAt(LocalDateTime.now());
        token.setExpiresAt(LocalDateTime.now().plusMinutes(expirationMinutes));

        otpTokenRepository.save(token);
        emailService.sendOtpMail(email, otp);
    }

    public void verifyOtp(String email, String otp, String purpose) {
        OtpToken token = otpTokenRepository.findTopByEmailAndPurposeOrderByCreatedAtDesc(email, purpose)
                .orElseThrow(() -> new InvalidOtpException("OTP not found"));

        if (token.isVerified()) throw new InvalidOtpException("OTP already verified");
        if (LocalDateTime.now().isAfter(token.getExpiresAt())) throw new InvalidOtpException("OTP expired");
        if (token.getAttempts() >= maxAttempts) throw new InvalidOtpException("Max OTP attempts exceeded");

        if (!otp.equals(token.getOtpCode())) {
            token.setAttempts(token.getAttempts() + 1);
            otpTokenRepository.save(token);
            throw new InvalidOtpException("Invalid OTP");
        }

        token.setVerified(true);
        otpTokenRepository.save(token);
    }
}

