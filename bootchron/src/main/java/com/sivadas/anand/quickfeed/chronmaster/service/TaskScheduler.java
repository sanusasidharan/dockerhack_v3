/*
 * Copyright (c) 2017 Author(s). All rights reserved.
 * 
 * @Author	- Anand Sivadas
 * @Date	- 15 Jan, 2017
 * 
 * 
 */

package com.sivadas.anand.quickfeed.chronmaster.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * The Class TaskScheduler.
 */
@Component
public class TaskScheduler {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskScheduler.class);

    /** The Constant dateFormat. */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    @Autowired
    private RSSPollerService pollService;

    /**
     * Poll infy rss feeds.
     */
	// examples of other CRON expressions
	// * "0 0 * * * *" = the top of every hour of every day.
	// * "*/10 * * * * *" = every ten seconds.
	// * "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
	// * "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
	// * "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
	// * "0 0 0 25 12 ?" = every Christmas Day at midnight
    @Scheduled(cron="*/15 * * * * ?")
    public void pollInfyRSSFeeds() {
        pollService.pollForFeeds("Infosys");
        pollService.pollForFeeds("blog");
        pollService.pollForFeeds("media");
        pollService.pollForFeeds("investor");
        LOGGER.info("The time is now {}", dateFormat.format(new Date()));
    }

}
