DROP TABLE IF EXISTS rssfeed;
CREATE TABLE rssfeed(
    rss_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50),
    published_date DATE,
    link_ VARCHAR(50),
    description VARCHAR(250),
    source_ VARCHAR(25)
);
