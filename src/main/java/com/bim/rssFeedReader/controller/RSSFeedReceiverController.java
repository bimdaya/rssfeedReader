package com.bim.rssFeedReader.controller;

import com.bim.rssFeedReader.common.RSSFeedConstants;
import com.bim.rssFeedReader.common.RSSFeedCustomException;
import com.bim.rssFeedReader.modal.RSSFeedItem;
import com.bim.rssFeedReader.repository.RSSFeedJDBCRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class RSSFeedReceiverController {

    private static final Logger logger = LoggerFactory.getLogger(RSSFeedReceiver.class);
    private static final RSSFeedReceiver rssFeedReceiver = new RSSFeedReceiver();

    @Autowired
    private RSSFeedJDBCRepository rssFeedJDBCRepository;

    @Scheduled(fixedDelay = 1000, initialDelay = 0)
    public void reportCurrentTime() {
        try {
            List<RSSFeedItem> rssFeedItemList = rssFeedReceiver.getRSSFeedItemsIterate();
            for (RSSFeedItem rssFeedItem : rssFeedItemList) {
                rssFeedJDBCRepository.insertRssFeed(rssFeedItem);
            }
        } catch (RSSFeedCustomException e) {
            logger.error("Could not run scheduled task to get RSS Feeds from URL: " + RSSFeedConstants.RSS_BASE_URL
                    + "\n" + e.getErrorMessage());
        }
    }

}
