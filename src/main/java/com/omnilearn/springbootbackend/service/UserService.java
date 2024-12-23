package com.omnilearn.springbootbackend.service;

import com.omnilearn.springbootbackend.Dto.RegisterDto;
import com.omnilearn.springbootbackend.model.User;
import com.omnilearn.springbootbackend.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.omnilearn.springbootbackend.utils.EmailUtils;
import com.omnilearn.springbootbackend.utils.OtpUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    OtpUtils otpUtils;

    @Autowired
    private EmailUtils emailUtils;
    @Autowired
    UserRepository userRepository;
    @Transactional

    public User isEmailExists(String emailId) {
        User user = userRepository.findByemailId(emailId);

        if(user!=null){
            return user;
        }
        else{
            return null;
        }
    }


    public List<User> findAllUsers(){
        return userRepository.findAll();
    }


    public void updatePassword(String emailId, String newHashedPassword) {
       User user= userRepository.findByemailId(emailId);
       user.setPassword(newHashedPassword);
       userRepository.save(user);
    }
    public String register (RegisterDto registerDto){
        String otp=otpUtils.generateOtp();
        try {
            emailUtils.sendOtpEmail(registerDto.getEmailId(),otp);
        }catch (MessagingException e){
            throw new RuntimeException("unable to send otp,please try again");
        }
        User user=new User();
        user.setName(registerDto.getName());
        user.setEmailId(registerDto.getEmailId());
        user.setCollege(registerDto.getCollege());
        user.setCompany(registerDto.getCompany());
        user.setPassword(registerDto.getPassword());
        user.setUsername(registerDto.getUsername());
        user.setOtp(otp);
        user.setOTpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "User registration successful";


    }

    public String verifyAccount(String emailId, String otp) {
        User user=userRepository.findByemailId(emailId);
        if(user.getOtp().equals(otp) && Duration.between(user.getOTpGeneratedTime(),LocalDateTime.now()).getSeconds()<5*60)
        {
            user.setActive(true);
            userRepository.save(user);
            return "Verified";
        }
        return "notVerified";

    }

    public String regenrateOtp(String emailId) {
        User user=userRepository.findByemailId(emailId);
        String otp=otpUtils.generateOtp();
        try {
            emailUtils.sendOtpEmail(emailId,otp);
        }catch (MessagingException e){
            throw new RuntimeException("unable to send otp,please try again");
        }
        user.setOtp(otp);
        user.setOTpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "Sent";
    }
}
