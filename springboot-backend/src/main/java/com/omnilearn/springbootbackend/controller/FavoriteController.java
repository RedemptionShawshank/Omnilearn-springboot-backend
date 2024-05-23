package com.omnilearn.springbootbackend.controller;

import com.omnilearn.springbootbackend.model.*;
import com.omnilearn.springbootbackend.repository.PlateformCourseListRepository;
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
//@CrossOrigin(origins = "https://omnilearn.vercel.app")
//@CrossOrigin(origins = "http://localhost:4200")
public class FavoriteController {

    @Autowired
    UserService userService;


    @Autowired
    private TopicService topicService;

    @Autowired
    private FavouriteService favouriteService;


    @PostMapping("/favList")
    public List<Favorite> receivedUserName(@RequestBody UserFav body){

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


//        boolean check = favouriteService.isCourseFavouriteForUser(favourite.getUserName(),favourite.getCourseId());

        if(!favouriteService.isCourseFavouriteForUser(favourite.getUserName(),favourite.getCourseId())){
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




}
