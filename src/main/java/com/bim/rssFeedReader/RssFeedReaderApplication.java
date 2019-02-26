package com.bim.rssFeedReader;

import com.bim.rssFeedReader.controller.RSSFeedReaderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RssFeedReaderApplication {

	@Autowired
	RSSFeedReaderController rssFeedReaderController;

	public static void main(String[] args) {
		SpringApplication.run(RssFeedReaderApplication.class, args);
	}

}
