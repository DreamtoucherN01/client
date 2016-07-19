package com.blake.client;

import twitter4j.conf.ConfigurationBuilder;

import com.blake.search.PeriodSearch;
import com.blake.util.Constants;


public class SpiderTopicChannel  implements Runnable {

	public void run() {

		
		ConfigurationBuilder cb = new ConfigurationBuilder();  
		 cb.setDebugEnabled(true)  
       	.setOAuthConsumerKey(Constants.consumerKey)  
       	.setOAuthConsumerSecret(Constants.consumerSecret)  
       	.setOAuthAccessToken(Constants.accessToken)  
       	.setOAuthAccessTokenSecret(Constants.accessTokenKey);  
		 cb.setJSONStoreEnabled(true);
		
		 
		String sinceDate = "2016-02-01";
		String []topicKey = {"fort mcmurray fire"};
		
//		String endDate = "2016-02-01";
		System.out.println("topic channel open now");
		for(int i = 0; i < topicKey.length ; i++) {
			
			new PeriodSearch(cb).search(topicKey[i], Integer.MAX_VALUE, sinceDate, null);
		}
		
	}

}
