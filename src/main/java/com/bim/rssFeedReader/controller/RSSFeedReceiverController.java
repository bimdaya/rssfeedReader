package com.bim.rssFeedReader.controller;

import com.bim.rssFeedReader.common.RSSFeedConstants;
import com.bim.rssFeedReader.common.RSSFeedCustomException;
import com.bim.rssFeedReader.modal.RSSFeedItem;
import com.bim.rssFeedReader.repository.RSSFeedJDBCRepository;
import com.bim.rssFeedReader.service.RSSFeedReceiverService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 *
 * Manage(only the Insert function is implemented from CRUD ) RSS feeds in H2 database
 *
 * @author Bimali Dayananda
 */
@Configuration
@EnableScheduling
public class RSSFeedReceiverController implements RSSFeedReceiverService {

    private static final Logger logger = LoggerFactory.getLogger(RSSFeedReceiver.class);
    private static final RSSFeedReceiver rssFeedReceiver = new RSSFeedReceiver();

    @Autowired
    private RSSFeedJDBCRepository rssFeedJDBCRepository;

    /**
     *
     * Insert RSS Feeds to H2 database
     */
    @Override
    @Scheduled(fixedDelay = 1000, initialDelay = 0)
    public void insertRssFeedList() {
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
