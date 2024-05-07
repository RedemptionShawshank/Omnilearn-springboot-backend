package com.omnilearn.springbootbackend.repository;

import com.omnilearn.springbootbackend.model.User;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserRepositoryImpl{
    @Autowired
     EntityManager entityManager;


    public  User getUser(long i){
        Session session=entityManager.unwrap(Session.class);
        return session.get(User.class,i);
    }

    public List<User> getAllUsers(){

        Session session = entityManager.unwrap(Session.class); // physical connection to database

        List<User> allUsers = session.createQuery("SELECT U FROM User U",User.class).getResultList();
        return allUsers;

    }





}
