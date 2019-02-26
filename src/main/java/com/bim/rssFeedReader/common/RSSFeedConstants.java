package com.bim.rssFeedReader.common;

public class RSSFeedConstants {

    //RSS Database constants
    public final static String RSSFEED_DB_NAME = "rssfeed";
    public final static String RSSFEED_DB_RSS_ID = "rss_id";
    public final static String RSSFEED_DB_RSS_TITLE = "title";
    public final static String RSSFEED_DB_RSS_PUBLISHED_DATE = "published_date";
    public final static String RSSFEED_DB_RSS_LINK = "link_";
    public final static String RSSFEED_DB_RSS_DESCRIPTION = "description";
    public final static String RSSFEED_DB_RSS_SOURCE = "source_";

    public final static int MAX_TOP_VALUES = 10;

    //Return values
    public final static int SUCCESS_CODE = 0;
    public final static int ERROR_CODE = -1;

    // REST attributes
    public final static String REST_RETURN_DATA_TYPE = "application/json";

    //RSS Configurations
    public final static String RSS_BASE_URL = "https://news.google.com/news/rss";

}
