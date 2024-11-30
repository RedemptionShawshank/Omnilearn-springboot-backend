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

        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        mimeMessage.setFrom("omnilearn56@gmail.com");
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage);
        String emailBody = String.format("""
            <div>
                <p>Hello,</p>
                <p>Your OTP for verification is: <strong>%s</strong></p>
            </div>
            """, otp, email, otp);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("verify Otp");
        mimeMessageHelper.setText(
                emailBody,true);
        javaMailSender.send(mimeMessage);

    }
}
