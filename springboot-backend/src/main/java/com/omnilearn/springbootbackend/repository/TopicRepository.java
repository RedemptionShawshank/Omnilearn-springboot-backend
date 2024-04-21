package com.omnilearn.springbootbackend.repository;

import com.omnilearn.springbootbackend.model.TOPIC_LIST;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<TOPIC_LIST,Long> {

}
