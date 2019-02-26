package com.bim.rssFeedReader.modal;

import java.util.Date;

public class RSSFeedItem {
    private String title;
    private String link;
    private Date publishedDate;
    private String source;
    private String description;
    private Long id;

    public RSSFeedItem() {

    }

    public RSSFeedItem(String title, String link, Date publishedDate, String description, String source) {
        this.setTitle(title);
        this.setLink(link);
        this.setPublishedDate(publishedDate);
        this.setDescription(description);
        this.setSource(source);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
