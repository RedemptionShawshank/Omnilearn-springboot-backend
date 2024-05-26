package com.omnilearn.springbootbackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TOPIC_LIST")
public class TOPIC_LIST {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TOPIC_NAME")
    private String topicName;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "DESCRIPTION")
    private String description;

    public TOPIC_LIST(){

    }
    public TOPIC_LIST(String topicName, String imagePath,String type,String description) {
        super();
        this.topicName = topicName;
        this.imagePath = imagePath;
        this.type = type;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
