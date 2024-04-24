package com.omnilearn.springbootbackend.repository;

import com.omnilearn.springbootbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> { //JpaRepository exposes lots of inbuilt methods

    User findByemailId(String emailId); // here jpa automatically generates an internal query to search through database, so the variable that need to be searched should be provided carefully

}
