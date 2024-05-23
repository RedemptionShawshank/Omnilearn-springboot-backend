package com.omnilearn.springbootbackend.repository;

import com.omnilearn.springbootbackend.model.PLATFORM_COURSE_LIST;
import com.omnilearn.springbootbackend.model.TOPIC_LIST;
import com.omnilearn.springbootbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<TOPIC_LIST,Long> {
}
