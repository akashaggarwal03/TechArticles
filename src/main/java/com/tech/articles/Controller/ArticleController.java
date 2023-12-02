package com.tech.articles.Controller;

import com.rometools.rome.io.FeedException;
import com.tech.articles.Models.Article;
import com.tech.articles.Services.RssReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {


//    @Autowired
//    private ArticleRepository articleRepository;

    @Autowired
    private RssReaderService rssReaderService;

    @GetMapping("/latest")
    public List<Article> getLatestArticles() throws FeedException, IOException {
        // Implement logic to fetch and return the latest articles
        // You can add additional filtering, sorting, or limit the number of articles
        return rssReaderService.readRssFeed("https://engineering.fb.com/feed/");
    }
}
