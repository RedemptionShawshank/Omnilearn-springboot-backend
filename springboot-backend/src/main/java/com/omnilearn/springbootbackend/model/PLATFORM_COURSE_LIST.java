package com.omnilearn.springbootbackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PLATFORM_COURSE_LIST")
public class PLATFORM_COURSE_LIST {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "PLATFORM_NAME")
    private String platformName;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "AFFILIATE_LINK")
    private String affiliateLink;

    @Column(name = "DESCRIPTION")
    private String desc;

    @Column(name = "TOPIC_NAME")
    private String topicName;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    @Column(name = "RATING")
    private String rating;

    public PLATFORM_COURSE_LIST(String platformName, Integer price, String affiliateLink, String desc, String topicName, String imagePath, String rating) {
        super();
        this.platformName = platformName;
        this.price = price;
        this.affiliateLink = affiliateLink;
        this.desc = desc;
        this.topicName = topicName;
        this.imagePath = imagePath;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
