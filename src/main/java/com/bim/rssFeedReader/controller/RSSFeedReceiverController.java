package com.bim.rssFeedReader.controller;

import com.bim.rssFeedReader.common.RSSFeedCustomException;
import com.bim.rssFeedReader.modal.RSSFeedItem;
import com.bim.rssFeedReader.repository.RSSFeedJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class RSSFeedReceiverController {

    private static final RSSFeedReceiver rssFeedReceiver = new RSSFeedReceiver();
    @Autowired
    private RSSFeedJDBCRepository rssFeedJDBCRepository;

    @Scheduled(fixedDelay = 1000, initialDelay = 0)
    public void reportCurrentTime() throws RSSFeedCustomException {
        List<RSSFeedItem> rssFeedItemList = rssFeedReceiver.getRSSFeedItemsIterate();
        for (RSSFeedItem rssFeedItem : rssFeedItemList) {
            rssFeedJDBCRepository.insertRssFeed(rssFeedItem);
        }
    }

}
