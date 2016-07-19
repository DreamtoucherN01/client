package com.blake.search;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.blake.util.Constants;
import com.blake.util.HttpRequest;
import com.google.gson.Gson;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import twitter4j.Query;
import twitter4j.Query.ResultType;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class PeriodSearch implements Search {

	
	Twitter twitter ;
	ConfigurationBuilder cb;
	QueryResult result = null;
	
	public PeriodSearch(ConfigurationBuilder cb) {
		this.cb = cb;
		twitter = new TwitterFactory(cb.build()).getInstance();
	}

	public <T> List<T> search(String key) {

		return search(key,100,null);
	}

	public <T> List<T> search(String key, int count, String sinceDate) {

		return search(key,count, sinceDate, null);
	}

	public <T> List<T> search(String key, int count, String sinceDate,
			String endDate) {

		Query query=new Query();
        try {
        	
        	query.setResultType(ResultType.mixed);
        	query.setQuery(key);        	
            query.setCount(count);//设置每次获取数量
            if(sinceDate!=null){
            	
            	query.setSince(sinceDate);
            }
           if(endDate!=null){
        	   
        	   query.until(endDate);
           }
            do {
            	
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                dealTweets(tweets);
                
            } while ((query = result.nextQuery()) != null);
        } catch (TwitterException te) {
        	
            te.printStackTrace();
        }
       
		return null;
	}

	private void dealTweets(List<Status> tweets) {

		for (Status tweet : tweets) {
			
			Gson gson=new Gson();
		    DBObject dbObject = (DBObject) JSON.parse(gson.toJson(tweet));
		    
		    JSONObject jo = new JSONObject();
	        jo.put("type", "insert");
	        jo.put("body", dbObject.toString());
		    
		    HttpRequest.sendPost(Constants.serverUrl, jo.toString());
			System.out.println("data source from topic : " + dbObject); 
		}
	}

	public List<String> res2String(List<Status> tweets) {
		
		List<String> infos=new ArrayList<String>();
		for (Status tweet : tweets) {
			
            infos.add(tweet.toString());
		}
		return infos;
	}

	public <T> List<T> search(String key, int count) {
		// TODO Auto-generated method stub
		return null;
	}

}
