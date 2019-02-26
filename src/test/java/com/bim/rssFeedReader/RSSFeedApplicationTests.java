package com.bim.rssFeedReader;

import com.bim.rssFeedReader.modal.RSSFeedItem;
import com.bim.rssFeedReader.repository.RSSFeedJDBCRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:templates/data.sql")
public class RSSFeedApplicationTests {

    @Autowired
    private RSSFeedJDBCRepository rssFeedJDBCRepository;

    @Test
    public void testRSSFeedReaderControllerSize() {
        List<RSSFeedItem> rssFeedItemList = rssFeedJDBCRepository.getTopRssFeeds();
        Assert.assertEquals(rssFeedItemList.size() , 10);
    }

    @Test
    public void  testRSSFeedReaderControllerDataTitle() {
        RSSFeedItem rssFeedItem = rssFeedJDBCRepository.getTopRssFeed();
        Assert.assertEquals(rssFeedItem.getTitle(), "Sample Title 1");

    }

}
