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

    @GetMapping("/users")
    public List<User> getAllUsers(){

//        List<User> u = userService.findAllUsers();
        List<User> u = userService.getAllusers();



        for(int i=0;i<u.size();i++){
            User u1 = u.get(i);
            System.out.println(u1.getUsername() + ' ' + u1.getId());
        }

        return u; //implement all these functions in service class
    }

    @GetMapping("/topic_list")
    public List<TOPIC_LIST> getTopicList(){

        return topicRepository.findAll();

    }


    @PostMapping("/path-variable")
    public List<PLATFORM_COURSE_LIST> receiveImageName(@RequestBody String imageName) {
        // Process the image name
        System.out.println("Received image name: " + imageName);
        return topicService.getTopic(imageName);

        // Do something with the image name (e.g., save to database)
       // return "received";
    }

    @PostMapping("/favList")
    public List<Favorite> receivedUserName(@RequestBody UserFav body){

        System.out.println("received user name: " + body.getUserName() + " " + body.getTopicName());
        List<Favorite> favorites = favouriteService.getFavoriteList(body.getUserName(),body.getTopicName());
        return favorites;
    }

    @PostMapping("/userFavlist")
    public List<Favorite> getFavouriteListByUsername(@RequestBody String userName){

        List<Favorite> favorites = favouriteService.getFavoriteListByUsername(userName);
        return favorites;
    }

    @PostMapping("/addFavourite")
    public List<Favorite> addFavourite(@RequestBody Favorite favourite){


        Favorite check = favouriteService.isCourseIdExists(favourite.getCourseId());

        if(check == null){
            return favouriteService.addfavourite(favourite);
        }
        else{
            return favouriteService.getFavouriteList();
        }
    }

    @DeleteMapping("/remove/{courseId}")
    public void removeFavourite(@PathVariable Integer courseId){
        favouriteService.removeFavourite(courseId);
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
        else if(!check.isActive())
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(registerDto.getPassword());
        registerDto.setPassword(hashedPassword);
        return new ResponseEntity<>(userService.register(registerDto), HttpStatus.OK);
    }
    @PutMapping("/verify-account")
    public ResponseEntity<String>verifyAccount(@RequestParam String email,@RequestParam String otp){
        return new ResponseEntity<>(userService.verifyAccount(email,otp),HttpStatus.OK);
    }
    @PutMapping("/regenerate-otp")
    public ResponseEntity<String> RegenrateOtp(@RequestParam String email){
        return new ResponseEntity<>(userService.regenrateOtp(email),HttpStatus.OK);
    }

}
