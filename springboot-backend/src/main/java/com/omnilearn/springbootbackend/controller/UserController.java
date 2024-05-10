package com.omnilearn.springbootbackend.controller;

import com.omnilearn.springbootbackend.model.*;
import com.omnilearn.springbootbackend.repository.PlateformCourseListRepository;
import com.omnilearn.springbootbackend.repository.TopicRepository;
import com.omnilearn.springbootbackend.repository.UserRepository;
import com.omnilearn.springbootbackend.service.FavouriteService;
import com.omnilearn.springbootbackend.service.TopicService;
import com.omnilearn.springbootbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "https://omnilearn.vercel.app")
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
        else{
            return null;
        }

    }


}
