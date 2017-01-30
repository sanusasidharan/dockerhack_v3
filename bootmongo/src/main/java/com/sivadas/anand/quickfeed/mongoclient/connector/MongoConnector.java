/*
 * Copyright (c) 2017 Author(s). All rights reserved.
 * 
 * @Author	- Anand Sivadas
 * @Date	- Jan 25, 2017
 * 
 * 
 */
package com.sivadas.anand.quickfeed.mongoclient.connector;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * The Class MongoConnector initializes a MongoDB connection on OpenShift. </br>
 * Reads environment properties for MongoDB hostname,
 * port, userid/pwd and initializes a MongoClient with the properties.
 * 
 *
 */
public class MongoConnector {

	/** The mongo db host name. */
	private static String mongoDBHostName;
	
	/** The mongo db port. */
	private static String mongoDBPort;
	
	/** The mongo db database name. */
	private static String mongoDBDatabaseName;
	
	/** The mongo db user name. */
	private static String mongoDBUserName;
	
	/** The mongo db password. */
	private static String mongoDBPassword;
	
	/** The port. */
	private static Integer port;

	/** The mongo client. */
	private static MongoClient mongoClient;
	
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MongoConnector.class);
	/*
	 * If running in OpenShift environment, read System environment properties,
	 * otherwise if not found (not running on OpenShift), read properties
	 * from -D command-line properties.
	 */
	static {
		mongoDBDatabaseName = System.getenv("OPENSHIFT_GEAR_NAME");
		if (mongoDBDatabaseName == null){
			mongoDBDatabaseName = System.getProperty("OPENSHIFT_GEAR_NAME");
		}
		//try property for MongoDB in container
		if (mongoDBDatabaseName == null){
			mongoDBDatabaseName = System.getenv("MONGODB_DB_NAME");
		}		
		if (mongoDBDatabaseName == null){
			mongoDBDatabaseName = "mydb"; //hardcoded local db for testing
		}
		
		LOGGER.info("MongoDB database name (OPENSHIFT_GEAR_NAME / MONGODB_DB_NAME): " + mongoDBDatabaseName);
		
		mongoDBHostName = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
		if(mongoDBHostName == null){
			mongoDBHostName = System.getenv("MONGODB_DB_HOST");
		}
		if(mongoDBHostName == null){
			mongoDBHostName = System.getProperty("OPENSHIFT_MONGODB_DB_HOST");
		}
		
		mongoDBPort = System.getenv("OPENSHIFT_MONGODB_DB_PORT");
		if(mongoDBPort == null){
			mongoDBPort = System.getenv("MONGODB_DB_PORT");
		}
		if(mongoDBPort == null){
			mongoDBPort = System.getProperty("OPENSHIFT_MONGODB_DB_PORT");
		}
		if(mongoDBPort == null){
			throw new QuickFeedMongoConnectorException("Property not found: OPENSHIFT_MONGODB_DB_PORT / MONGODB_DB_PORT");
		}
		else{
			port = Integer.valueOf(mongoDBPort);
		}
		
		mongoDBUserName = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");		
		mongoDBPassword = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
		

	}

	/**
	 * Instantiates a new mongo connection.
	 */
	private MongoConnector(){
	}
	
	
	/**
	 * Gets the mongo db.
	 *
	 * @return the mongo db
	 * @throws UnknownHostException the unknown host exception
	 */
	public static DB getMongoDB() throws UnknownHostException {
		MongoCredential credential = null;

		if (mongoClient == null) {

			if (mongoDBUserName != null && mongoDBPassword != null) {
				credential = MongoCredential.createMongoCRCredential(mongoDBUserName,
						mongoDBDatabaseName, mongoDBPassword.toCharArray());

				LOGGER.info("Opening connection to Mongo with credentials: "
						+ mongoDBHostName + " : " + port);
				mongoClient = new MongoClient(new ServerAddress(mongoDBHostName, port),
						Arrays.asList(credential));
			} else {
				LOGGER.info("Opening connection to Mongo without credentials: "
						+ mongoDBHostName + " : " + port);
				mongoClient = new MongoClient(new ServerAddress(mongoDBHostName, port));
			}
		}
		DB mongoDB = mongoClient.getDB(mongoDBDatabaseName);

		return mongoDB;

	}
}
