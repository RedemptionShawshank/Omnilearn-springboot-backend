package com.omnilearn.springbootbackend.service;

import com.omnilearn.springbootbackend.model.Favorite;
import com.omnilearn.springbootbackend.model.User;
import com.omnilearn.springbootbackend.repository.FavoriteRepository;
import com.omnilearn.springbootbackend.repository.FavouriteRepositoryImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private FavouriteRepositoryImpl favouriteRepositoryImpl;

    public List<Favorite> getFavoriteList(String userName,String topicName){

        List<Favorite> f = favouriteRepositoryImpl.getFavouriteList(userName,topicName);

        return f;

    }

    public List<Favorite> getFavoriteListByUsername(String username){
        return favoriteRepository.findByuserName(username);
    }

    public List<Favorite> addfavourite(Favorite favorite){
        favoriteRepository.save(favorite);
        return favoriteRepository.findAll();

    }

    public List<Favorite> getFavouriteList(){
        return favoriteRepository.findAll();
    }

    public Favorite isCourseIdExists(Integer courseId) {
        Favorite favorite = favoriteRepository.findByCourseId(courseId);

        if(favorite!=null){
            return favorite;
        }
        else{
            return null;
        }
    }

    @Transactional
    public void removeFavourite(Integer courseId){
        favoriteRepository.deleteBycourseId(courseId);
    }
}
