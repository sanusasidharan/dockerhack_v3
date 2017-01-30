/*
 * Copyright (c) 2017 Author(s). All rights reserved.
 * 
 * @Author	- Anand Sivadas
 * @Date	- Jan 25, 2017
 * 
 * 
 */
package com.sivadas.anand.quickfeed.mongoclient.connector;

/**
 * The Class QuickFeedMongoConnectorException.
 */
public class QuickFeedMongoConnectorException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5571749086069897417L;

	/**
	 * Instantiates a new mongo connection missing property exception.
	 *
	 * @param msg the msg
	 */
	public QuickFeedMongoConnectorException(String msg) {
		super(msg);
	}

}
