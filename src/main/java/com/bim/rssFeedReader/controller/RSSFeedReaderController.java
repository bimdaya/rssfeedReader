package com.bim.rssFeedReader.controller;

import com.bim.rssFeedReader.modal.RSSFeedItem;
import com.bim.rssFeedReader.repository.RSSFeedJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/")
@RestController()
public class RSSFeedReaderController {
    @Autowired
    RSSFeedJDBCRepository rssFeedJDBCRepository;

    @GetMapping(path = "home", produces = "application/json")
    public List<RSSFeedItem> getRssFeeds(){
        return rssFeedJDBCRepository.getTopRssFeeds();
    }

}
