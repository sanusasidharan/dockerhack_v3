/*
 * Copyright (c) 2017 Author(s). All rights reserved.
 * 
 * @Author	- Anand Sivadas
 * @Date	- 13 Jan, 2017
 * 
 * 
 */
package com.sivadas.anand.quickfeed.rsspoller;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rometools.rome.io.FeedException;
import com.sivadas.anand.quickfeed.rsspoller.parser.RomeRSSPaser;

/**
 * The Class RomeTester.
 */
public class RomeTester {

	/** The rss parser. */
	private static RomeRSSPaser rssParser;
	
	/**
	 * Before.
	 */
	@BeforeClass
	public static void before() {
		RomeTester.rssParser = new RomeRSSPaser();
	}
	
	/**
	 * After.
	 */
	@AfterClass
	public static void after() {
		RomeTester.rssParser = null;
	}
	
	/**
	 * Test ROME parser.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws FeedException the feed exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testRomeParser() throws IllegalArgumentException, FeedException, IOException {
		rssParser.parseRSSFeed("https://www.infosys.com/index.xml");
		rssParser.parseRSSFeed("https://www.infosys.com/index.xml");
		rssParser.parseRSSFeed("https://www.infosys.com/index.xml");
		rssParser.parseRSSFeed("https://www.infosys.com/index.xml");
	}

	/**
	 * Test extract feed name.
	 */
	@Test
	public void testExtractFeedName() {
		String extractFeedName = rssParser.extractFeedName("Infosys Blogs RSS");
		assertTrue("extractFeedName was = " + extractFeedName, StringUtils.equalsIgnoreCase("Blogs", extractFeedName));
		extractFeedName = rssParser.extractFeedName("Infosys Blogs RSS");
		assertTrue("extractFeedName was = " + extractFeedName, StringUtils.equalsIgnoreCase("Blogs", extractFeedName));
		extractFeedName = rssParser.extractFeedName("Infosys Investors RSS");
		assertTrue("extractFeedName was = " + extractFeedName, StringUtils.equalsIgnoreCase("Investors", extractFeedName));
		extractFeedName = rssParser.extractFeedName("Infosys RSS");
		assertTrue("extractFeedName was = " + extractFeedName, StringUtils.equalsIgnoreCase("INFOSYS", extractFeedName));
	}

}
