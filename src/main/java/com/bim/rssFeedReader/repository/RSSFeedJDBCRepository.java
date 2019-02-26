package com.bim.rssFeedReader.repository;

import com.bim.rssFeedReader.common.RSSFeedConstants;
import com.bim.rssFeedReader.modal.RSSFeedItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * Manage RSS feeds in H2 database
 *
 * @author Bimali Dayananda
 */
@Repository
public class RSSFeedJDBCRepository {

    private static final Logger logger = LoggerFactory.getLogger(RSSFeedJDBCRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Get RSS feeds in rssfeed table
     *
     * @return List of RSSFeedItem beans
     */
    public List<RSSFeedItem> getTopRssFeeds() {
        return jdbcTemplate.query("SELECT TOP " + RSSFeedConstants.MAX_TOP_VALUES + " * FROM "
                        + RSSFeedConstants.RSSFEED_DB_NAME, new rssFeedRowMapper());
    }

    /**
     * Get RSS first feed in rssfeed table(Use in Test cases)
     *
     * @return RSSFeedItem bean
     */
    public RSSFeedItem getTopRssFeed() {
        return jdbcTemplate.queryForObject("SELECT TOP 1 * FROM " + RSSFeedConstants.RSSFEED_DB_NAME,
                new BeanPropertyRowMapper<>(RSSFeedItem.class));
    }

    /**
     * Get RSS first feed in rssfeed table(Use in Test cases)
     *
     * @param rssFeedItem RSSFeedItem bean
     * @return success/fail, 0/-1
     */
    public int insertRssFeed(RSSFeedItem rssFeedItem) {
        if (rssFeedItem != null) {
            return jdbcTemplate.update("INSERT INTO " + RSSFeedConstants.RSSFEED_DB_NAME + " ("
                            + RSSFeedConstants.RSSFEED_DB_RSS_TITLE + ","
                            + RSSFeedConstants.RSSFEED_DB_RSS_LINK + ","
                            + RSSFeedConstants.RSSFEED_DB_RSS_PUBLISHED_DATE + ","
                            + RSSFeedConstants.RSSFEED_DB_RSS_SOURCE + ","
                            + RSSFeedConstants.RSSFEED_DB_RSS_DESCRIPTION + " ) "
                            + " VALUES (?, ?, ?, ?, ?)",
                    rssFeedItem.getTitle(), rssFeedItem.getLink(), rssFeedItem.getPublishedDate(),
                    rssFeedItem.getSource(), rssFeedItem.getDescription());
        } else {
            logger.warn("Object RSSFeedItem is empty. Data is not added to the database: "
                    + RSSFeedConstants.RSSFEED_DB_NAME);
            return RSSFeedConstants.ERROR_CODE;
        }
    }

    /**
     * Implementation of RowMapper<> interface
     */
    class rssFeedRowMapper implements RowMapper<RSSFeedItem> {

        @Override
        public RSSFeedItem mapRow(ResultSet resultSet, int i) {
            try {
                RSSFeedItem rssFeedItem = new RSSFeedItem();
                rssFeedItem.setId(resultSet.getLong(RSSFeedConstants.RSSFEED_DB_RSS_ID));
                rssFeedItem.setTitle(resultSet.getString(RSSFeedConstants.RSSFEED_DB_RSS_TITLE));
                rssFeedItem.setLink(resultSet.getString(RSSFeedConstants.RSSFEED_DB_RSS_LINK));
                rssFeedItem.setPublishedDate(resultSet.getDate(RSSFeedConstants.RSSFEED_DB_RSS_PUBLISHED_DATE));
                rssFeedItem.setSource(resultSet.getString(RSSFeedConstants.RSSFEED_DB_RSS_SOURCE));
                rssFeedItem.setDescription(resultSet.getString(RSSFeedConstants.RSSFEED_DB_RSS_DESCRIPTION));
                return rssFeedItem;
            } catch (SQLException e) {
                logger.error("Database: " + RSSFeedConstants.RSSFEED_DB_NAME
                        + ", does not return complete result set for RSS ID: " + RSSFeedConstants.RSSFEED_DB_RSS_ID
                        + "\n" + e);
                return null;
            }
        }

    }

}
