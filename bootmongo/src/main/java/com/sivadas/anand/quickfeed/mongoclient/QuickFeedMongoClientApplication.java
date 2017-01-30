/*
 * Copyright (c) 2017 Author(s). All rights reserved.
 * 
 * @Author	- Anand Sivadas
 * @Date	- Jan 25, 2017
 * 
 * 
 */
package com.sivadas.anand.quickfeed.mongoclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class QuickFeedMongoClientApplication.
 */
@SpringBootApplication
public class QuickFeedMongoClientApplication {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(QuickFeedMongoClientApplication.class);
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(QuickFeedMongoClientApplication.class, args);

	}
	
}
