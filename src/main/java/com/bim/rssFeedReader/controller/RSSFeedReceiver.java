package com.bim.rssFeedReader.controller;

import com.bim.rssFeedReader.modal.RSSFeedItem;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RSSFeedReceiver {

    List<RSSFeedItem> getRSSFeedItemsIterate(String feedSourceURL) {
        return getRSSFeedItems(feedSourceURL);
    }

    private List<RSSFeedItem> getRSSFeedItems(String feedSourceURL) {
        try {

            List<RSSFeedItem> rssFeedItemList = new ArrayList<>();

            if (feedSourceURL != null) {

                SyndFeedInput syndFeedInput = new SyndFeedInput();
                SyndFeed syndFeed = syndFeedInput.build(new XmlReader(new URL(feedSourceURL)));
                List<SyndEntry> syndEntryList = syndFeed.getEntries();

                for (SyndEntry syndEntry : syndEntryList) {
                    String rssTitle = syndEntry.getTitle();
                    String rssLink = syndEntry.getLink();
                    String rssSource = getSource(String.valueOf(syndEntry.getSource()));
                    String rssDescription = getDescrption(String.valueOf(syndEntry.getDescription()));
                    Date rssPublishedDate = syndEntry.getPublishedDate();

                    rssFeedItemList.add(new RSSFeedItem(rssTitle, rssLink, rssPublishedDate, rssDescription, rssSource));
                }
            }

            return rssFeedItemList;

        } catch (IllegalArgumentException | IOException | FeedException e) {
            throw new RuntimeException("Error getting feed from ");
        }
    }

    private String getSource(String source) {
        return (source != null) ? source : "Source not found.";
    }

    private String getDescrption(String description) {
        String formattedDescription = null;
        if (description != null) {
            formattedDescription = description.substring(description.indexOf("<"));
        }
        return formattedDescription;
    }

}
