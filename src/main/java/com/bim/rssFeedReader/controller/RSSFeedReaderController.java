package com.bim.rssFeedReader.controller;

import com.bim.rssFeedReader.common.RSSFeedConstants;
import com.bim.rssFeedReader.common.RSSFeedCustomException;
import com.bim.rssFeedReader.modal.RSSFeedItem;
import com.bim.rssFeedReader.repository.RSSFeedJDBCRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.http.HTTPException;
import java.util.List;

@RequestMapping("/")
@RestController()
public class RSSFeedReaderController {
    @Autowired
    RSSFeedJDBCRepository rssFeedJDBCRepository;

    @GetMapping(path = "home", produces = RSSFeedConstants.REST_RETURN_DATA_TYPE)
    public List<RSSFeedItem> getRSSFeeds() throws RSSFeedCustomException {
        List<RSSFeedItem> rssFeedItemList = rssFeedJDBCRepository.getTopRssFeeds();
        if (rssFeedItemList.isEmpty()) {
            throw new RSSFeedCustomException("RSSFeeds are not found in the database: "
                    + RSSFeedConstants.RSSFEED_DB_NAME + " from the URL: "
                    +  RSSFeedConstants.RSS_BASE_URL , HttpStatus.NO_CONTENT);
        }
        return rssFeedItemList;
    }

}
