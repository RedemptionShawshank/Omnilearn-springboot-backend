package com.omnilearn.springbootbackend.repository;

import com.omnilearn.springbootbackend.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Long> {

//    List<Favorite> findByuserNameandtopicName(String userName,String topicName);
    Favorite findByCourseId(Integer courseId);

    void deleteBycourseId(Integer courseId);
}
