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

    @Column(name = "PLATFORM_NAME")
    private String platformName;
    @Column(name = "PRICE")
    private Integer price;
    @Column(name = "AFFILIATE_LINK")
    private String affiliateLink;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "IMAGE_PATH")
    private String imagePath;
    @Column(name = "RATING")
    private String rating;


    public Favorite(String userName, String topicName, Integer courseId, String platformName, Integer price, String affiliateLink, String description, String imagePath, String rating) {
        super();
        this.userName = userName;
        this.topicName = topicName;
        this.courseId = courseId;
        this.platformName = platformName;
        this.price = price;
        this.affiliateLink = affiliateLink;
        this.description = description;
        this.imagePath = imagePath;
        this.rating = rating;
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

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAffiliateLink() {
        return affiliateLink;
    }

    public void setAffiliateLink(String affiliateLink) {
        this.affiliateLink = affiliateLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
