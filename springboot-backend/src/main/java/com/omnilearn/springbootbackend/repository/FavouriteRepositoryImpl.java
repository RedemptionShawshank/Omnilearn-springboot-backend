package com.omnilearn.springbootbackend.repository;


import com.omnilearn.springbootbackend.model.Favorite;
import com.omnilearn.springbootbackend.model.User;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavouriteRepositoryImpl {

    @Autowired
    EntityManager entityManager;


    public List<Favorite> getFavouriteList(String userName,String topicName){

        Session session = entityManager.unwrap(Session.class); // physical connection to database

        List<Favorite> allList = session.createQuery("SELECT f FROM Favorite f WHERE f.userName = :userName AND f.topicName = :topicName", Favorite.class)
                .setParameter("userName", userName)
                .setParameter("topicName", topicName)
                .getResultList();

        return allList;

    }
}
