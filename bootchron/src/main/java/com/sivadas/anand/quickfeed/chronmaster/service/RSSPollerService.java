package com.sivadas.anand.quickfeed.chronmaster.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.sivadas.anand.quickfeed.integration.domain.Feeds;

@Component
public class RSSPollerService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(RSSPollerService.class);

	@Value("${bootrest.instance.serviceUrl}")
	private  String rssFeedsServiceURL;
	
	@Value("${bootmongo.instance.serviceUrl}")
	private  String mongoDBServiceURL;
	
	public String pollForFeeds(String feedCategory) {
		LOGGER.info("polling started --> ");
		RestTemplate restTemplate = new RestTemplate();
		LOGGER.debug("Feeds URL = " + rssFeedsServiceURL + feedCategory);
		Feeds feeds = restTemplate.getForObject(rssFeedsServiceURL + feedCategory, Feeds.class);
		LOGGER.debug("feeds = " + feeds);
		if (null != feeds && !ObjectUtils.isEmpty(feeds.getFeeds())) {
			LOGGER.debug("Mongo URL = " + mongoDBServiceURL);
			ResponseEntity<String> postForEntity = restTemplate.postForEntity(mongoDBServiceURL, feeds, String.class);
			HttpStatus statusCode = postForEntity.getStatusCode();
			LOGGER.debug("Post status = " + statusCode.toString());
		}
		
		return null;
	}

}
