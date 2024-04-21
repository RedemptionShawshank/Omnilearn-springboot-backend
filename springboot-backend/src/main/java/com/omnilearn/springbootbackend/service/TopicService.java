package com.omnilearn.springbootbackend.service;

import com.omnilearn.springbootbackend.model.PLATFORM_COURSE_LIST;
import com.omnilearn.springbootbackend.repository.PlateformCourseListRepository;
import com.omnilearn.springbootbackend.repository.PlateformCourseListRepositoryImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TopicService {
    @Autowired
    PlateformCourseListRepositoryImpl plateformCourseListRepositoryImpl;
    @Autowired
    PlateformCourseListRepository plateformCourseListRepository;

    @Transactional
    public List<PLATFORM_COURSE_LIST> getTopic(String imageName){
        List<PLATFORM_COURSE_LIST>topicArray=new ArrayList<>();
        long count =plateformCourseListRepository.count();
       // System.out.println(count);
        for(long i=1;i<=count;i++){
            PLATFORM_COURSE_LIST topic=plateformCourseListRepositoryImpl.getTopic(i);
           // System.out.println(topic.getTopicName());
            if(topic.getTopicName().equals(imageName))
            {
                topicArray.add(topic);
                System.out.println(topic.getPlatformName());
            }
        }
        return topicArray;
    }


}
