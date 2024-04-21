package com.omnilearn.springbootbackend.repository;

import com.omnilearn.springbootbackend.model.PLATFORM_COURSE_LIST;
import com.omnilearn.springbootbackend.model.TOPIC_LIST;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlateformCourseListRepository extends JpaRepository<PLATFORM_COURSE_LIST,Long> {

}
