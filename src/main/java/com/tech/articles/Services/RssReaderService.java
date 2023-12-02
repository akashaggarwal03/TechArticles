package com.tech.articles.Services;



import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.tech.articles.Models.Article;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
public class RssReaderService {

    public List<Article> readRssFeed(String rssFeedUrl) throws IOException, FeedException {
        // Use Apache HttpClient to fetch the RSS feed

        URL feedSource = new URL(rssFeedUrl);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedSource));
        Iterator itr = feed.getEntries().iterator();
        List<Article> results = new ArrayList<>();
        while (itr.hasNext()) {
            SyndEntry syndEntry = (SyndEntry) itr.next();
            results.add(mapToArticle(syndEntry));
        }

        return results;
    }

    /**
     * Map to Article
     * @param syndEntry
     */
    private static Article mapToArticle(SyndEntry syndEntry) {
        Article newsArticle = new Article();

        newsArticle.setTitle(syndEntry.getTitle());
     //   newsArticle.setPubDate(syndEntry.getPublishedDate().toString());
       // newsArticle.setImgUrl("");
        newsArticle.setLink(syndEntry.getLink());
        return newsArticle;
    }
}