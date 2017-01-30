/*
 * Copyright (c) 2017 Author(s). All rights reserved.
 * 
 * @Author	- Anand Sivadas
 * @Date	- 13 Jan, 2017
 * 
 * 
 */
package com.sivadas.anand.quickfeed.rsspoller.parser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.sivadas.anand.quickfeed.integration.domain.FeedMessage;
import com.sivadas.anand.quickfeed.integration.domain.Feeds;

/**
 * The Class RomeRSSPaser.
 */
public class RomeRSSPaser {

	/**
	 * Parses the RSS feed.
	 *
	 * @param rssURL the rss feed url
	 * @return the list of parsed Feeds
	 * @throws MalformedURLException the malformed url exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws FeedException the feed exception
	 */
	public static Feeds parseRSSFeed(String rssURL)
			throws MalformedURLException, IOException, FeedException {

		List<FeedMessage> feedMessages = new ArrayList<FeedMessage>();
		URL url = new URL(rssURL);
		HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
		// Reading the feed
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(httpcon));
		List<SyndEntry> entries = feed.getEntries();
		String feedTitle = feed.getTitle();
		String feedCategory = extractFeedName(feedTitle);
//		String imageURL = feed.getImage().getUrl();

		for (Object object : entries) {
			SyndEntry entry = (SyndEntry) object;
			FeedMessage feedMessage = new FeedMessage();
			feedMessage.setTitle(entry.getTitle());
			feedMessage.setDescription(entry.getDescription().getValue());
			feedMessage.setLink(entry.getLink());
			feedMessage.setPubDate(entry.getPublishedDate());
			feedMessage.setAuthor(entry.getAuthor());
			feedMessage.setFeedCategory(feedCategory);
			feedMessages.add(feedMessage);
		}
		
		Feeds feeds = new Feeds();
		feeds.setCategory(feedCategory);
		feeds.setChronTime(new Date());
		feeds.setFeeds(feedMessages);

		return feeds;
	}

	/**
	 * Extract feed name from feed title.
	 *
	 * @param feedTitle the feed title is defaulted to INFOSYS
	 * @return the extracted string for feed title
	 */
	public static String extractFeedName(String feedTitle) {

		String feedType = "INFOSYS";

		if (StringUtils.isNotBlank(feedTitle)) {
			String strippedfeedTitle = StringUtils.removeIgnoreCase(
					StringUtils.removeIgnoreCase(feedTitle, "Infosys"), "RSS");
			feedType = StringUtils.isNotBlank(strippedfeedTitle) 
						? StringUtils.upperCase(StringUtils.deleteWhitespace(strippedfeedTitle))
						: feedType;
		}

		return feedType;
	}

}
