DROP TABLE IF EXISTS rssfeed;
CREATE TABLE rssfeed(
    rss_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(500),
    published_date DATE,
    link_ VARCHAR(500),
    description VARCHAR(2000),
    source_ VARCHAR(25)
);
