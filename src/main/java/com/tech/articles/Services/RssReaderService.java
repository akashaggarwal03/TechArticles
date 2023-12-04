package com.tech.articles.Services;



import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.tech.articles.Models.Article;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedReader;
@Component
public class RssReaderService {

    public List<Article> readRssFeed(List<String> rssFeedUrls) throws IOException, FeedException {
        // Use Apache HttpClient to fetch the RSS feed
        List<Article> articles = new ArrayList<>();

        for(String rssFeedUrl: rssFeedUrls){

            URL url = new URL(rssFeedUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            // Set up HTTP GET request
            connection.setRequestMethod("GET");
            // Get the input stream
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(connection.getInputStream()));
            connection.disconnect();



            Iterator itr = feed.getEntries().iterator();
            while (itr.hasNext()) {
                SyndEntry syndEntry = (SyndEntry) itr.next();
                articles.add(mapToArticle(syndEntry));
            }
        }


        return articles;
    }

    /**
     * Map to Article
     * @param syndEntry
     */
    private static Article mapToArticle(SyndEntry syndEntry) {
        Article newsArticle = new Article();

        newsArticle.setTitle(syndEntry.getTitle());
       // newsArticle.setPubDate(syndEntry.getPublishedDate().toString());
        newsArticle.setImgUrl("");
        newsArticle.setLink(syndEntry.getLink());
        return newsArticle;
    }
}