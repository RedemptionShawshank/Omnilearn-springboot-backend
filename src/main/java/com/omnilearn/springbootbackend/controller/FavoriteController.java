package com.omnilearn.springbootbackend.controller;

import com.omnilearn.springbootbackend.model.*;
import com.omnilearn.springbootbackend.service.FavouriteService;
import com.omnilearn.springbootbackend.service.TopicService;
import com.omnilearn.springbootbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")

public class FavoriteController {

    @Autowired
    UserService userService;


    @Autowired
    private TopicService topicService;

    @Autowired
    private FavouriteService favouriteService;


    // to get the list of all the favourite courses of the user for a particular topic -----------
    @GetMapping("/favList")
    public List<Favorite> receivedUserName(@RequestBody UserFav body){

        List<Favorite> favorites = favouriteService.getFavoriteList(body.getUserName(),body.getTopicName());
        return favorites;
    }


    // to get the list of all the favourite courses of the user --------------------
    @GetMapping("/userFavlist")
    public List<Favorite> getFavouriteListByUsername(@RequestBody String userName){

        List<Favorite> favorites = favouriteService.getFavoriteListByUsername(userName);
        return favorites;
    }

    // add favourite course for the user
    @PostMapping("/addFavourite")
    public List<Favorite> addFavourite(@RequestBody Favorite favourite){

        if(!favouriteService.isCourseFavouriteForUser(favourite.getUserName(),favourite.getCourseId())){
            return favouriteService.addfavourite(favourite);
        }
        else{
            return favouriteService.getFavouriteList();
        }
    }

    // to remove a favourite course
    @DeleteMapping("/remove/{courseId}")
    public void removeFavourite(@PathVariable Integer courseId){
        favouriteService.removeFavourite(courseId);
    }
}
