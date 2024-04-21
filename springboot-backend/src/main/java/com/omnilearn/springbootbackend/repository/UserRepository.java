package com.omnilearn.springbootbackend.repository;

import com.omnilearn.springbootbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> { //JpaRepository exposes lots of inbuilt methods

}
