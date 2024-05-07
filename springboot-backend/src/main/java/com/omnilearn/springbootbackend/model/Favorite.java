package com.omnilearn.springbootbackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FAVOURITE")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "TOPIC_NAME")
    private String topicName;

    @Column(name = "COURSE_ID")
    private Integer courseId;

    public Favorite(long id, String userName, String topicName, Integer courseId) {
        super();
        this.userName = userName;
        this.topicName = topicName;
        this.courseId = courseId;
    }

    public Favorite(){

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
