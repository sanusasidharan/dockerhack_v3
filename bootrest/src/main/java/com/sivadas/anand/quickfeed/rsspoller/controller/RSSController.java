/*
 * Copyright (c) 2017 Author(s). All rights reserved.
 * 
 * @Author	- Anand Sivadas
 * @Date	- Jan 27, 2017
 * 
 * 
 */
package com.sivadas.anand.quickfeed.rsspoller.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rometools.rome.io.FeedException;
import com.sivadas.anand.quickfeed.integration.domain.Feeds;
import com.sivadas.anand.quickfeed.rsspoller.parser.RomeRSSPaser;

/**
 * The Class RSSController.
 */
@RestController
public class RSSController {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RSSController.class);

	/** The infy feed. */
	@Value("${rss.feed.infosys}")
	private  String infyFeed;
	
	/** The investor feed. */
	@Value("${rss.feed.investor}")
	private  String investorFeed;
	
	/** The media feed. */
	@Value("${rss.feed.media}")
	private  String mediaFeed;
	
	/** The blog feed. */
	@Value("${rss.feed.blog}")
	private  String blogFeed;
	
	/** The counter. */
	private final AtomicLong counter = new AtomicLong();
	
	/**
	 * Rssfeed.
	 *
	 * @param category the category
	 * @return the feeds
	 */
	@RequestMapping("/rssfeed")
	public Feeds rssfeed(@RequestParam(value="cat", defaultValue="INFOSYS") String category) {
		
		String feedURL = infyFeed ;
		//@TODO change to switch case
		if (StringUtils.equalsIgnoreCase(StringUtils.upperCase(category), "investor")) {
			feedURL = investorFeed;
		} else if (StringUtils.equalsIgnoreCase(StringUtils.upperCase(category), "media")) {
			feedURL = mediaFeed;
		} else if (StringUtils.equalsIgnoreCase(StringUtils.upperCase(category), "blog")) {
			feedURL = blogFeed;
		} else {
			feedURL = infyFeed;
		}
		
		Feeds parseRSSFeed = null;
		try {
			parseRSSFeed = RomeRSSPaser.parseRSSFeed(feedURL);
		} catch (FeedException feedException) {
			LOGGER.error("FeedException", feedException);
		} catch (MalformedURLException murlException) {
			LOGGER.error("MalformedURLException", murlException);
		} catch (IOException ioException) {
			LOGGER.error("IOException", ioException);
		}
		
		return parseRSSFeed;
	}
	
}
