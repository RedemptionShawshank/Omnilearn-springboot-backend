package com.omnilearn.springbootbackend.service;

import com.omnilearn.springbootbackend.model.PLATFORM_COURSE_LIST;
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
//                System.out.println(topic.getPlatformName());
            }
        }
        return topicArray;
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
