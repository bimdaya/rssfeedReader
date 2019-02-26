package com.bim.rssFeedReader.repository;

import com.bim.rssFeedReader.modal.RSSFeedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RSSFeedJDBCRepository {

    //Since there are only a few constants I did not use a separate constant class
    private final static String RSSFEED_DB_NAME = "rssfeed";
    private final static String RSSFEED_DB_RSS_ID = "rss_id";
    private final static String RSSFEED_DB_RSS_TITLE = "title";
    private final static String RSSFEED_DB_RSS_PUBLISHED_DATE = "published_date";
    private final static String RSSFEED_DB_RSS_LINK = "link_";
    private final static String RSSFEED_DB_RSS_DESCRIPTION = "description";
    private final static String RSSFEED_DB_RSS_SOURCE = "source_";
    private final static int MAX_TOP_VALUES = 10;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    //jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
    //jdbcTemplate.execute("CREATE TABLE customers(" +
    //            "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");


    class rssFeedRowMapper implements RowMapper<RSSFeedItem> {

        @Override
        public RSSFeedItem mapRow(ResultSet resultSet, int i) throws SQLException {
            RSSFeedItem rssFeedItem = new RSSFeedItem();
            rssFeedItem.setId(resultSet.getLong(RSSFEED_DB_RSS_ID));
            rssFeedItem.set(resultSet.getString(RSS));
            rssFeedItem.setLink(resultSet.getString(RSSFEED_DB_LINK));
            rssFeedItem.setPublishedDate(resultSet.getDate(RSSFEED_DB_PUBLISHED_DATE));
            rssFeedItem.setTitle(resultSet.getString(RSSFEED_DB_TITLE));
            return rssFeedItem;
        }

    }

    public List<RSSFeedItem> findTopRssFeeds() {
        return jdbcTemplate.query("SELECT TOP " + MAX_TOP_VALUES + " * FROM " + RSSFEED_DB_NAME,
                new rssFeedRowMapper());
    }

    public int insertRssFeed(RSSFeedItem rssFeedItem) {
        return jdbcTemplate.update("INSERT INTO " + RSSFEED_DB_NAME + " ("
                        + RSSFEED_DB_AUTHOR_NAMES + ","
                        + RSSFEED_DB_LINK + ","
                        + RSSFEED_DB_PUBLISHED_DATE + ","
                        + RSSFEED_DB_TITLE + " ) "
                        + " VALUES (?, ?, ?, ?)",
                rssFeedItem.getAuthors(), rssFeedItem.getLink(), rssFeedItem.getPublishedDate(), rssFeedItem.getTitle());
    }

}
