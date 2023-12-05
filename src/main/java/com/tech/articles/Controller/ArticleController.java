package com.tech.articles.Controller;

import com.rometools.rome.io.FeedException;
import com.tech.articles.Models.Article;
import com.tech.articles.Services.GoogleSheetsService;
import com.tech.articles.Services.RssReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {


    @Autowired
    private RssReaderService rssReaderService;

    @GetMapping("/latest")
    @CrossOrigin(origins = "http://localhost:3000/")
    public List<Article> getLatestArticles() throws FeedException, IOException, GeneralSecurityException {
        // Implement logic to fetch and return the latest articles
        // You can add additional filtering, sorting, or limit the number of articles
        List<String> bloglinks = GoogleSheetsService.getTechArticles();
        return rssReaderService.readRssFeed(bloglinks);
    }
}
