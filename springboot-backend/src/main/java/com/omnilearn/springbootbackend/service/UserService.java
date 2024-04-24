package com.omnilearn.springbootbackend.service;

import com.omnilearn.springbootbackend.model.User;
import com.omnilearn.springbootbackend.repository.UserRepository;
import com.omnilearn.springbootbackend.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserRepositoryImpl userRepositoryImpl;

    @Autowired
    UserRepository userRepository;
    @Transactional
    public void getUser(){
        long count =userRepository.count();
        //for(int )
        for(long i=1;i<=count;i++){
            User user=userRepositoryImpl.getUser(i);
            System.out.println(user.getId());
            System.out.println(user.getUsername());
        }

    }

    public User isEmailExists(String emailId) {
        User user = userRepository.findByemailId(emailId);

        if(user!=null){
            return user;
        }
        else{
            return null;
        }
    }

}
