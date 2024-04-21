package com.omnilearn.springbootbackend.controller;

import com.omnilearn.springbootbackend.model.TOPIC_LIST;
import com.omnilearn.springbootbackend.model.User;
import com.omnilearn.springbootbackend.repository.TopicRepository;
import com.omnilearn.springbootbackend.repository.UserRepository;
import com.omnilearn.springbootbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

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
    public String receiveImageName(@RequestBody String imageName) {
        // Process the image name
        System.out.println("Received image name: " + imageName);
        // Do something with the image name (e.g., save to database)
        return "received";
    }

    // rest api for getting user info from angular app and saving it into database
    @PostMapping("/userInfo")
    public User addUserInfo(@RequestBody User user){
        String password = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);
        user.setPassword(hashedPassword);

        String emailId = user.getEmailId();
        boolean check= userService.isEmailExists(emailId);
        System.out.println("email find: "+ check);

        return userRepository.save(user);
    }

    @PostMapping("/loginInfo")
    public Object loggingIN(@RequestBody Object loginInfo){

        System.out.println("received login info: "+ loginInfo);

        return loginInfo;

    }


}
