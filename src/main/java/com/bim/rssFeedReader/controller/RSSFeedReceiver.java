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

/**
 *
 * Receives and modify RSS feeds from the RSS_BASE_URL
 *
 * @author Bimali Dayananda
 */
class RSSFeedReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RSSFeedReceiver.class);

    List<RSSFeedItem> getRSSFeedItemsIterate() throws RSSFeedCustomException {
        return getRSSFeedItems();
    }

    /**
     * Get RSS feed items list
     *
     * @return List of RSSFeedItem beans
     * @throws RSSFeedCustomException when SyndFeed could not build XML returned from RSS_BASE_URL
     */
    private List<RSSFeedItem> getRSSFeedItems() throws RSSFeedCustomException {
        try {

            List<RSSFeedItem> rssFeedItemList = new ArrayList<>();

            SyndFeedInput syndFeedInput = new SyndFeedInput();
            SyndFeed syndFeed = syndFeedInput.build(new XmlReader(new URL(RSSFeedConstants.RSS_BASE_URL)));

            if (syndFeed != null) {
                List<SyndEntry> syndEntryList = syndFeed.getEntries();

                //Map SyndEntry values to rssFeedItem
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
                    + "No feeds found in the XML.");
                throw new RSSFeedCustomException("Error while retrieving RSS Feeds from URL: "
                        + RSSFeedConstants.RSS_BASE_URL);
            }

            return rssFeedItemList;

        } catch (IOException | FeedException e) {
            throw new RSSFeedCustomException("Error while getting feed from URL: " + RSSFeedConstants.RSS_BASE_URL
                + "\n Unable to build the XML");
        }
    }

    /**
     * Modify if source attribute value is empty in SyndFeed object
     *
     * @param  source value in SyndFeed object
     * @return String modified source value
     */
    private String getSource(String source) {
        return (source != null) ? source : "Source not found.";
    }

    /**
     * Modify description attribute value in SyndFeed object
     *
     * @param  description value in SyndFeed object
     * @return String modified description value
     */
    private String getDescription(String description) {
        String formattedDescription;
        if (description != null) {
            //Remove additional data assigned to description value while parsing the XML to SyndFeed object
            formattedDescription = description.substring(description.indexOf("<"));
        } else {
            formattedDescription = "Description not found.";
        }
        return formattedDescription;
    }

}
