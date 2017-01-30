/*
 * Copyright (c) 2017 Author(s). All rights reserved.
 * 
 * @Author	- Anand Sivadas
 * @Date	- 13 Jan, 2017
 * 
 * 
 */
package com.sivadas.anand.quickfeed.integration.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The Class FeedMessage.
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class FeedMessage extends BaseDTO {

	private static final long serialVersionUID = -4862468905688655545L;

	/** The title. */
	private String title;

	/** The description. */
	private String description;

	/** The link. */
	private String link;

	/** The author. */
	private String author;

	/** The guid. */
	private String guid;

	/** The pub date. */
	private Date pubDate;

	/** The feed category. */
	private String feedCategory;

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the link.
	 * 
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Sets the link.
	 * 
	 * @param link
	 *            the new link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Gets the author.
	 * 
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author.
	 * 
	 * @param author
	 *            the new author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets the guid.
	 * 
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * Sets the guid.
	 * 
	 * @param guid
	 *            the new guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * Gets the pub date.
	 * 
	 * @return the pub date
	 */
	public Date getPubDate() {
		return pubDate;
	}

	/**
	 * Sets the pub date.
	 * 
	 * @param pubDate
	 *            the new pub date
	 */
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * Gets the feed category.
	 * 
	 * @return the feed category
	 */
	public String getFeedCategory() {
		return feedCategory;
	}

	/**
	 * Sets the feed category.
	 * 
	 * @param feedCategory
	 *            the new feed category
	 */
	public void setFeedCategory(String feedCategory) {
		this.feedCategory = feedCategory;
	}

}
