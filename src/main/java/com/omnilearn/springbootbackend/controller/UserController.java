package com.omnilearn.springbootbackend.controller;

import com.omnilearn.springbootbackend.Dto.RegisterDto;
import com.omnilearn.springbootbackend.model.*;
import com.omnilearn.springbootbackend.repository.TopicRepository;
import com.omnilearn.springbootbackend.repository.UserRepository;
import com.omnilearn.springbootbackend.service.FavouriteService;
import com.omnilearn.springbootbackend.service.TopicService;
import com.omnilearn.springbootbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "https://omnilearn.vercel.app")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserService userService;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private FavouriteService favouriteService;


    @GetMapping("/topic_list")
    public List<TOPIC_LIST> getTopicList(){

        return topicRepository.findAll();

    }

    @GetMapping("/type/{type}")
    public List<TOPIC_LIST> getTopicByType(@PathVariable String type){

        return topicService.getTopicBytype(type);

    }
    @GetMapping("/id/{id}")
    public String getById(@PathVariable String id){
        return "OK";
    }


    @PostMapping("/addCourses")
    public String addCourses(@RequestBody List<Object> rows){

        return topicService.addCourses(rows);

    }


    @PostMapping("/path-variable")
    public List<PLATFORM_COURSE_LIST> receiveImageName(@RequestBody String imageName) {
        // Process the image name
        return topicService.getTopic(imageName);

        // Do something with the image name (e.g., save to database)
       // return "received";
    }




    // rest api for getting user info from angular app and saving it into database
//    @PostMapping("/userInfo")
//    public String addUserInfo(@RequestBody User user){
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String hashedPassword = encoder.encode(user.getPassword());
//        user.setPassword(hashedPassword);
//
//        User check= userService.isEmailExists(user.getEmailId());
//
//        if(check == null){
//            userRepository.save(user);
//            return "Account created successfully";
//        }
//        else{
//            return "Account Already Exists";
//        }
//
//    }

    @PostMapping("/loginInfo")
    public User loggingIN(@RequestBody LinkedHashMap<String,String> loginInfo){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User check = userService.isEmailExists(loginInfo.get("emailId"));

        if(check!=null && encoder.matches(loginInfo.get("password"),check.getPassword())){
            System.out.println("Authorized user");
            check.setPassword(null);
            return check;
        }
        else if(check!=null && !check.isActive())
            return null;
        else{
            return null;
        }

    }
    @PutMapping("/forget-password")
    public String forgetPassword(@RequestBody LinkedHashMap<String,String> body){
        User check = userService.isEmailExists(body.get("emailId"));
        if(check==null)
            return "Email id not present";
        else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String newHashedPassword = encoder.encode(body.get("password"));
            String emailId=body.get("emailId");
            userService.updatePassword(emailId,newHashedPassword);
            return "Password Changed Successfully";


        }

    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){

        User check= userService.isEmailExists(registerDto.getEmailId());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(registerDto.getPassword());
        registerDto.setPassword(hashedPassword);
        if(check == null){
            userService.register(registerDto);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/verify-account")
//    ResponseEntity<String>
    public String verifyAccount(@RequestBody OTPverification otpverification){
//        return new ResponseEntity<>(userService.verifyAccount(otpverification.getEmail(),otpverification.getOtp()),HttpStatus.OK);
        return userService.verifyAccount(otpverification.getEmail(),otpverification.getOtp());
    }
    @PutMapping("/regenerate-otp")
    public String RegenrateOtp(@RequestBody String email){
        return userService.regenrateOtp(email);

//        return new ResponseEntity<>(userService.regenrateOtp(email),HttpStatus.OK);
    }

}
