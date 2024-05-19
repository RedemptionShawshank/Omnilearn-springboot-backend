package com.omnilearn.springbootbackend.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
    @Autowired
    JavaMailSender javaMailSender;
    public void sendOtpEmail(String email, String otp) throws MessagingException {
//        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
//        simpleMailMessage.setTo(email);
//        simpleMailMessage.setSubject("Verify Otp");
//        simpleMailMessage.setText("Hello your otp is"+otp);
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage);
        String emailBody = String.format("""
            <div>
                <p>Hello,</p>
                <p>Your OTP for verification is: <strong>%s</strong></p>
                <p>Please click the following link to verify your account:</p>
                <a href="http://localhost:8080/api/v1/verify-account?email=%s&otp=%s" target="_blank">Verify Account</a>
            </div>
            """, otp, email, otp);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("verify Otp");
        mimeMessageHelper.setText(
//"""
//                <div>
//                <a href="http://localhost:8080/api/v1/verify-account?email=%s&otp=%s" target="_blank">click link to verify</a>
//                </div>
//
//                """.formatted(email,otp)
                emailBody,true);
        javaMailSender.send(mimeMessage);

    }
}
