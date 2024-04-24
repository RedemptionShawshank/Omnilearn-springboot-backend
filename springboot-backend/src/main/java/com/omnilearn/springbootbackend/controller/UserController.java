package com.omnilearn.springbootbackend.controller;

import com.omnilearn.springbootbackend.model.PLATFORM_COURSE_LIST;
import com.omnilearn.springbootbackend.model.TOPIC_LIST;
import com.omnilearn.springbootbackend.model.User;
import com.omnilearn.springbootbackend.repository.PlateformCourseListRepository;
import com.omnilearn.springbootbackend.repository.TopicRepository;
import com.omnilearn.springbootbackend.repository.UserRepository;
import com.omnilearn.springbootbackend.service.TopicService;
import com.omnilearn.springbootbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4200/courses/DSA"})
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserService userService;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicService topicService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        userService.getUser();

        return userRepository.findAll(); //implement all these functions in service class
    }

    @GetMapping("/topic_list")
    public List<TOPIC_LIST> getTopicList(){

        return topicRepository.findAll();

    }

//    @GetMapping("/{pathVariable}")
//    public String pathVariablefromAngular(@PathVariable String pathVariable) {
//       // String pathVariable = data;
//        System.out.println("path variable"+ pathVariable);
//        // Process the data received from the frontend
//        return "received";
//    }
//    @PostMapping("/path-variable")
//    public ResponseEntity<String> receiveImageName(@RequestBody String imageName) {
//        // Process the image name
//        System.out.println("Received image name: " + imageName);
//        // Do something with the image name (e.g., save to database)
//        return ResponseEntity.ok("Image name received: " + imageName);
//    }

    @PostMapping("/path-variable")
    public List<PLATFORM_COURSE_LIST> receiveImageName(@RequestBody String imageName) {
        // Process the image name
        System.out.println("Received image name: " + imageName);
        return topicService.getTopic(imageName);

        // Do something with the image name (e.g., save to database)
       // return "received";
    }

    // rest api for getting user info from angular app and saving it into database
    @PostMapping("/userInfo")
    public String addUserInfo(@RequestBody User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        User check= userService.isEmailExists(user.getEmailId());

        if(check == null){
            userRepository.save(user);
            return "Account created successfully";
        }
        else{
            return "Account Already Exists";
        }

    }

    @PostMapping("/loginInfo")
    public User loggingIN(@RequestBody LinkedHashMap<String,String> loginInfo){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User check = userService.isEmailExists(loginInfo.get("emailId"));

        if(encoder.matches(loginInfo.get("password"),check.getPassword())){
            System.out.println("Authorized user");
            check.setPassword(null);
            return check;
        }
        else{
            return null;
        }

    }


}
