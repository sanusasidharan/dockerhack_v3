/*
 * Copyright (c) 2017 Author(s). All rights reserved.
 * 
 * @Author	- Anand Sivadas
 * @Date	- Jan 25, 2017
 * 
 * 
 */
package com.sivadas.anand.quickfeed.integration.domain;

import java.util.Date;
import java.util.List;

/**
 * The Class Feeds.
 */
public class Feeds extends BaseDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2368617325084485371L;

	/** The category. */
	private String category;
	
	/** The feeds. */
	private List<FeedMessage> feeds;
	
	/** The chron time. */
	private Date chronTime;

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Gets the feeds.
	 *
	 * @return the feeds
	 */
	public List<FeedMessage> getFeeds() {
		return feeds;
	}

	/**
	 * Sets the feeds.
	 *
	 * @param feeds the new feeds
	 */
	public void setFeeds(List<FeedMessage> feeds) {
		this.feeds = feeds;
	}

	/**
	 * Gets the chron time.
	 *
	 * @return the chron time
	 */
	public Date getChronTime() {
		return chronTime;
	}

	/**
	 * Sets the chron time.
	 *
	 * @param chronTime the new chron time
	 */
	public void setChronTime(Date chronTime) {
		this.chronTime = chronTime;
	}

}
