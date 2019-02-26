package com.bim.rssFeedReader.controller;

import com.bim.rssFeedReader.common.RSSFeedConstants;
import com.bim.rssFeedReader.common.RSSFeedCustomException;
import com.bim.rssFeedReader.modal.RSSFeedItem;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class RSSFeedReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RSSFeedReceiver.class);

    List<RSSFeedItem> getRSSFeedItemsIterate() throws RSSFeedCustomException {
        return getRSSFeedItems();
    }

    private List<RSSFeedItem> getRSSFeedItems() throws RSSFeedCustomException {
        try {

            List<RSSFeedItem> rssFeedItemList = new ArrayList<>();

            SyndFeedInput syndFeedInput = new SyndFeedInput();
            SyndFeed syndFeed = syndFeedInput.build(new XmlReader(new URL(RSSFeedConstants.RSS_BASE_URL)));
            if (syndFeed != null) {
                List<SyndEntry> syndEntryList = syndFeed.getEntries();

                for (SyndEntry syndEntry : syndEntryList) {
                    String rssTitle = syndEntry.getTitle();
                    String rssLink = syndEntry.getLink();
                    String rssSource = getSource(String.valueOf(syndEntry.getSource()));
                    String rssDescription = getDescription(String.valueOf(syndEntry.getDescription()));
                    Date rssPublishedDate = syndEntry.getPublishedDate();

                    rssFeedItemList.add(new RSSFeedItem(rssTitle, rssLink, rssPublishedDate, rssDescription, rssSource));
                }
            } else {
                logger.error("Error while retrieving RSS Feeds from URL: " + RSSFeedConstants.RSS_BASE_URL
                    + "SyndFeed is empty.");
                throw new RSSFeedCustomException("Error while retrieving RSS Feeds from URL: "
                        + RSSFeedConstants.RSS_BASE_URL);
            }

            return rssFeedItemList;

        } catch (IllegalArgumentException | IOException | FeedException e) {
            throw new RuntimeException("Error getting feed from ");
        }
    }

    private String getSource(String source) {
        return (source != null) ? source : "Source not found.";
    }

    private String getDescription(String description) {
        String formattedDescription = null;
        if (description != null) {
            formattedDescription = description.substring(description.indexOf("<"));
        } else {
            formattedDescription = "Description not found.";
        }
        return formattedDescription;
    }

}
