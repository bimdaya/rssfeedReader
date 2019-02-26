package com.bim.rssFeedReader.controller;

import com.bim.rssFeedReader.modal.RSSFeedItem;
import com.bim.rssFeedReader.repository.RSSFeedJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class RSSFeedReceiverController {

    private static final RSSFeedReceiver rssFeedReceiver = new RSSFeedReceiver();
    @Value("${rrs.base.url}")
    private String rssFeedSourceURL;
    @Autowired
    private RSSFeedJDBCRepository rssFeedJDBCRepository;

    @Scheduled(fixedDelay = 1000, initialDelay = 0)
    public void reportCurrentTime() {
        List<RSSFeedItem> rssFeedItemList = rssFeedReceiver.getRSSFeedItemsIterate(rssFeedSourceURL);
        for (RSSFeedItem rssFeedItem : rssFeedItemList) {
            rssFeedJDBCRepository.insertRssFeed(rssFeedItem);
        }
    }

}
