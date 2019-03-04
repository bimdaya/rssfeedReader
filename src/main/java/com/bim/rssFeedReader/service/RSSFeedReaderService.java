package com.bim.rssFeedReader.service;

import com.bim.rssFeedReader.common.RSSFeedCustomException;
import com.bim.rssFeedReader.modal.RSSFeedItem;

import java.util.List;

/**
 *
 * Interface for RSS feeds reader service
 *
 * @author Bimali Dayananda
 */
public interface RSSFeedReaderService {

    /**
     * Get RSS feeds
     *
     * @return List of RSSFeedItem beans
     * @throws RSSFeedCustomException when RSSFeedItems are not loaded
     */
    public List<RSSFeedItem> getRSSFeeds() throws RSSFeedCustomException;

}
