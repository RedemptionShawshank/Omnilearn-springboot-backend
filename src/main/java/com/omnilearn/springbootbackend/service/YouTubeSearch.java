package com.omnilearn.springbootbackend.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.*;
import com.omnilearn.springbootbackend.model.PLATFORM_COURSE_LIST;
import com.omnilearn.springbootbackend.repository.PlateformCourseListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import java.util.List;
import java.util.Scanner;

@Service
public class YouTubeSearch {
    private static final String API_KEY = "AIzaSyAYFALIpoi0Aw2IpF5RviH-yvov99dnqXo"; // Replace with your API key
    private static final long MAX_RESULTS = 500;
    private static final long MIN_VIEWS = 50000;

    @Autowired
    PlateformCourseListRepository plateformCourseListRepository;

    public String addCourse(String customQuery,String topicName) {
        try {
//            Scanner scanner = new Scanner(System.in);
//
//            System.out.println("Enter your search query:");
//            String customQuery = scanner.nextLine();

            YouTube youtubeService = new YouTube.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    null
            ).setYouTubeRequestInitializer(new YouTubeRequestInitializer(API_KEY))
                    .setApplicationName("youtube-search-sample")
                    .build();

            YouTube.Search.List search = youtubeService.search().list("snippet");
            search.setQ(customQuery); // Use the custom search query provided by the user
            search.setMaxResults(MAX_RESULTS);
            search.setType("video");// Filter for videos popular in India
            search.setVideoDuration("long");// long means videos more than 20 mins length
            search.setRelevanceLanguage("en");

            SearchListResponse response = search.execute(); // executing search
            long totaoResults = response.getPageInfo().getTotalResults();
            System.out.println("total rusults: " + totaoResults);
            List<SearchResult> searchResultList = response.getItems();

            if (searchResultList != null) {
                int vidNum = 1;
                for (SearchResult result : searchResultList) {
                    String videoId = result.getId().getVideoId();

                    // Fetch video details to get view count and likes
                    YouTube.Videos.List videoRequest = youtubeService.videos().list("statistics,snippet");
                    videoRequest.setId(videoId);

                    YouTube.Videos.List videosList = youtubeService.videos().list("contentDetails");
                    videosList.setId(videoId);

//                    YouTube.Captions.List captionsList = youtubeService.captions().list("snippet", videoId);
//                    CaptionListResponse captionResponse = captionsList.execute();
//
//                    for(Caption cp:captionResponse.getItems()){
//                        System.out.println("language"+cp.getSnippet().getLanguage()+" ");
//                    }

                    VideoListResponse vResponse = videosList.execute();
                    Video v = vResponse.getItems().get(0);
                    String duration = v.getContentDetails().getDuration();
                    Duration d = DatatypeFactory.newInstance().newDuration(duration);
                    int h = d.getHours();
                    int m = d.getMinutes();
                    int s = d.getSeconds();
                    int minutes = h * 60 + m;

                    String VideoLength = h + ":" + m + ":" + s;

                    VideoListResponse videoResponse = videoRequest.execute();
                    Video video = videoResponse.getItems().get(0);
                    long viewCount = video.getStatistics().getViewCount().longValue();
                    long likeCount = video.getStatistics().getLikeCount().longValue();

                    double ratio = ((double) likeCount) / viewCount; //ratio >= 0.01


                    if (ratio >= 0.02 && viewCount > MIN_VIEWS && minutes >= 20) {
                        System.out.println("Title: " + vidNum + " " + result.getSnippet().getTitle());
                        String description = "Likes: "+likeCount+" Views: "+viewCount;
                        System.out.println("Description: " + description);
                        vidNum++;
                        System.out.println("Duration: " + VideoLength);
                        System.out.println("Views: " + viewCount);
                        System.out.println("Likes: " + likeCount);
                        String affiliateLink = "https://www.youtube.com/watch?v=" + videoId;
                        System.out.println(affiliateLink);
                        System.out.println("ratio: " + ratio);
                        System.out.println();

                        // Fetch top-level comments
                        YouTube.CommentThreads.List commentRequest = youtubeService.commentThreads()
                                .list("snippet")
                                .setVideoId(videoId)
                                .setMaxResults(5L); // Get top 5 comments

                        CommentThreadListResponse commentResponse = commentRequest.execute();
                        List<CommentThread> comments = commentResponse.getItems();

//                        System.out.println("Top Comments:");
//                        for (CommentThread comment : comments) {
//                            CommentSnippet snippet = comment.getSnippet().getTopLevelComment().getSnippet();
//                            System.out.println("Author: " + snippet.getAuthorDisplayName());
//                            System.out.println("Comment: " + snippet.getTextDisplay());
//                            System.out.println("Likes: " + snippet.getLikeCount());
//                            System.out.println("-----");
//                        }
                        System.out.println();

//                        PLATFORM_COURSE_LIST platformCourseList = new PLATFORM_COURSE_LIST("Youtube", 0, affiliateLink, description, topicName, "assets/images_courses/web-development.png", "n/a");
//                        plateformCourseListRepository.save(platformCourseList);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "added";
    }
}
