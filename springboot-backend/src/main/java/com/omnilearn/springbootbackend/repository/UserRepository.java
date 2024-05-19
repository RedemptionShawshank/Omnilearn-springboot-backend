package com.omnilearn.springbootbackend.repository;

import com.omnilearn.springbootbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> { //JpaRepository exposes lots of inbuilt methods

    Optional<User> findByemailId(String emailId); // here jpa automatically generates an internal query to search through database, so the variable that need to be searched should be provided carefully
    List<User> findAll();
}
