package com.bim.rssFeedReader.controller;

import com.bim.rssFeedReader.common.RSSFeedConstants;
import com.bim.rssFeedReader.common.RSSFeedCustomException;
import com.bim.rssFeedReader.modal.RSSFeedItem;
import com.bim.rssFeedReader.repository.RSSFeedJDBCRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * Manage(only the GET call is implemented ) RSS feeds
 *
 * @author Bimali Dayananda
 */
@RequestMapping("/")
@RestController()
public class RSSFeedReaderController {

    @Autowired
    private RSSFeedJDBCRepository rssFeedJDBCRepository;

    /**
     * Get RSS feeds
     *
     * @return List of RSSFeedItem beans
     * @throws RSSFeedCustomException when RSSFeedItems are not loaded
     */
    @GetMapping(path = "home", produces = RSSFeedConstants.REST_RETURN_DATA_TYPE)
    public List<RSSFeedItem> getRSSFeeds() throws RSSFeedCustomException {
        List<RSSFeedItem> rssFeedItemList = rssFeedJDBCRepository.getTopRssFeeds();
        if (rssFeedItemList.isEmpty()) {
            throw new RSSFeedCustomException("RSSFeeds are not found in the database: "
                    + RSSFeedConstants.RSSFEED_DB_NAME + " from the URL: "
                    + RSSFeedConstants.RSS_BASE_URL , HttpStatus.NO_CONTENT.value());
        }
        return rssFeedItemList;
    }

}
