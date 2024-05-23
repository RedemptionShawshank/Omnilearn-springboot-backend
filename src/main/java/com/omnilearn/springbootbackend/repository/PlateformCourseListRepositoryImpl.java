package com.omnilearn.springbootbackend.repository;

import com.omnilearn.springbootbackend.model.PLATFORM_COURSE_LIST;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlateformCourseListRepositoryImpl {
    @Autowired
    EntityManager entityManager;

    public PLATFORM_COURSE_LIST getTopic(long i){
        Session session=entityManager.unwrap(Session.class);
        return session.get(PLATFORM_COURSE_LIST.class,i);
    }
}
