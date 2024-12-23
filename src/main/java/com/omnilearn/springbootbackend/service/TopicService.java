package com.omnilearn.springbootbackend.service;

import com.omnilearn.springbootbackend.model.PLATFORM_COURSE_LIST;
import com.omnilearn.springbootbackend.model.TOPIC_LIST;
import com.omnilearn.springbootbackend.repository.PlateformCourseListRepository;
import com.omnilearn.springbootbackend.repository.PlateformCourseListRepositoryImpl;
import com.omnilearn.springbootbackend.repository.TopicRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopicService {
    @Autowired
    PlateformCourseListRepositoryImpl plateformCourseListRepositoryImpl;
    @Autowired
    PlateformCourseListRepository plateformCourseListRepository;

    @Autowired
    TopicRepository topicRepository;


    @Transactional
    public List<PLATFORM_COURSE_LIST> getTopic(String imageName){

        return plateformCourseListRepository.getBytopicName(imageName);
    }

    public List<TOPIC_LIST> getTopicBytype(String type){

        return topicRepository.findBytype(type);

    }

    public String addCourses(List<Object> rows){

        List<PLATFORM_COURSE_LIST> pl = new ArrayList<>();


        for(int i=0;i<rows.size();i++){
            Map<String,String> mp = (Map<String, String>) rows.get(i);
            int price = Integer.parseInt(mp.get("price"));
            PLATFORM_COURSE_LIST p1 = new PLATFORM_COURSE_LIST((String) mp.get("platformName"), price, (String)mp.get("affiliateLink"), (String)mp.get("desc"), (String)mp.get("topicName"), (String)mp.get("imagePath"),(String) mp.get("rating"));
            plateformCourseListRepository.save(p1);
        }

        return "added";

    }


}
