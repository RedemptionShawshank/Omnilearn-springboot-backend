package com.omnilearn.springbootbackend.repository;

import com.omnilearn.springbootbackend.model.User;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl{
    @Autowired
     EntityManager entityManager;


    public  User getUser(long i){
        Session session=entityManager.unwrap(Session.class);
        return session.get(User.class,i);
    }





}
