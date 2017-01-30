package com.sivadas.anand.quickfeed.mongoclient.resource;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.sivadas.anand.quickfeed.integration.domain.FeedMessage;
import com.sivadas.anand.quickfeed.integration.domain.Feeds;
import com.sivadas.anand.quickfeed.mongoclient.connector.MongoConnector;

@Component
@Path("/feeds")
public class RSSFeedsResource {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RSSFeedsResource.class);
	
	/**
	 * Find and increment a sequence value for addressId from the sequences collection.
	 * @return
	 * @throws Exception
	 */
	public int getNextSequence() throws Exception {
		DB db = MongoConnector.getMongoDB();

		DBCollection sequences = db.getCollection("sequences");

		// fields to return
		DBObject fields = BasicDBObjectBuilder.start()
				.append("_id", 1)
				.append("value", 1).get();
		
		DBObject result = sequences.findAndModify(
				new BasicDBObject("_id", "addressId"), //query 
				fields, // what fields to return
				null, // no sorting
				false, //we don't remove selected document
				new BasicDBObject("$inc", new BasicDBObject("value", 1)), //increment value
				true, //true = return modified document
				true); //true = upsert, insert if no matching document

		return (Integer)result.get("value");
	}

	public void logEnvVars(){
		LOGGER.info("MONGODB_HOST_NAME: " + System.getenv("MONGODB_HOST_NAME"));
		LOGGER.info("MONGODB_DB_NAME: " + System.getenv("MONGODB_DB_NAME"));
		LOGGER.info("MONGODB_DB_PORT: " + System.getenv("MONGODB_DB_PORT"));
	}
	
	public String getServerIpAddress() throws UnknownHostException{
		InetAddress host = InetAddress.getLocalHost();
		String ip = host.getHostAddress();
		return ip;
	}
	
	//@GET
	//@Path("feeds")
	//@Produces(MediaType.APPLICATION_JSON)
	public Response getFeedsByCategory(@QueryParam("cat") String feedCategory) throws Exception {
		
		this.logEnvVars();
		
		DB db = MongoConnector.getMongoDB();
		
		DBObject query = new BasicDBObject("feedCategory", Pattern.compile(feedCategory));
		DBCursor c = db.getCollection("feeds").find(query);
		String jsonString = JSON.serialize(c);
		
		Response response = Response.status(Status.OK).entity(jsonString.toString()).build();
		return response;
	}	
	
	/**
	 * GET /feeds/{id}
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFeed(@PathParam("id") Long id) throws Exception {
		
		this.logEnvVars();
		DB db = MongoConnector.getMongoDB();
		
		DBObject query = new BasicDBObject("_id", id);
		DBCursor c = db.getCollection("feeds").find(query);
		String jsonString = JSON.serialize(c);
		
		Response response = Response.status(Status.OK).entity(jsonString.toString()).build();
		return response;
	}
	

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllFeeds(@QueryParam("cat") String feedCategory) throws Exception {
		
		DB db = MongoConnector.getMongoDB();
		DBCursor dbCursor = null;
		
		DBObject query = null;
		if(feedCategory != null){
			query = new BasicDBObject("feedCategory", Pattern.compile(feedCategory));
			dbCursor = db.getCollection("feeds").find(query);
		}
		else{		
			dbCursor = db.getCollection("feeds").find();
		}
		
		String jsonString = JSON.serialize(dbCursor);

		JSONArray json = new JSONArray(jsonString);
		json.put(new JSONObject().put("server-ip", this.getServerIpAddress()));
		System.out.println(json.toString());
		Response response = Response.status(Status.OK).entity(json.toString()).build();
		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertFeedMessage(FeedMessage feedMessage) throws Exception {
		DBObject feed = BasicDBObjectBuilder.start()
				.append("_id", this.getNextSequence())
				.append("title", feedMessage.getTitle())
				.append("description", feedMessage.getDescription())
				.append("link", feedMessage.getLink())
				.append("author", feedMessage.getAuthor())
				.append("pubDate", feedMessage.getPubDate())
				.append("feedCategory", feedMessage.getFeedCategory()).get();
		DB db = MongoConnector.getMongoDB();
		db.getCollection("feeds").insert(feed);
		Response response = Response.status(Status.OK).build();
		
		return response;
	}
	
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertFeeds(Feeds feeds) throws Exception {
		
		List<FeedMessage> feedMessages = feeds.getFeeds();
		for (FeedMessage feedMessage : feedMessages) {
			DBObject feed = BasicDBObjectBuilder.start()
					.append("_id", this.getNextSequence())
					.append("title", feedMessage.getTitle())
					.append("description", feedMessage.getDescription())
					.append("link", feedMessage.getLink())
					.append("author", feedMessage.getAuthor())
					.append("pubDate", feedMessage.getPubDate())
					.append("feedCategory", feedMessage.getFeedCategory()).get();
			DB db = MongoConnector.getMongoDB();
			db.getCollection("feeds").insert(feed);
		}
		
//		Response response = Response.status(Status.OK).build();
		JSONArray json = new JSONArray();
		json.put(new JSONObject().put("server-ip", this.getServerIpAddress()));
		System.out.println(json.toString());
		Response response = Response.status(Status.OK).entity(json.toString()).build();
		
		return response;
	}
}

