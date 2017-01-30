/*
 * Copyright (c) 2017 Author(s). All rights reserved.
 * 
 * @Author	- Anand Sivadas
 * @Date	- Jan 25, 2017
 * 
 * 
 */
package com.sivadas.anand.quickfeed.mongoclient;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.sivadas.anand.quickfeed.mongoclient.resource.RSSFeedsResource;

// TODO: Auto-generated Javadoc
/**
 * The Class JerseyAppConfig.
 */
@Component
public class JerseyAppConfig extends ResourceConfig {

	/**
	 * Instantiates a new jersey app config.
	 */
	public JerseyAppConfig() {
		register(RSSFeedsResource.class);
	}

}
