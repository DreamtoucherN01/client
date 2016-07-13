package com.blake.client;

import twitter4j.conf.ConfigurationBuilder;

import com.blake.search.PeriodSearch;
import com.blake.util.Constants;


public class SpiderTopicChannel  implements Runnable {

	@Override
	public void run() {

		
		ConfigurationBuilder cb = new ConfigurationBuilder();  
		 cb.setDebugEnabled(true)  
       	.setOAuthConsumerKey(Constants.consumerKey)  
       	.setOAuthConsumerSecret(Constants.consumerSecret)  
       	.setOAuthAccessToken(Constants.accessToken)  
       	.setOAuthAccessTokenSecret(Constants.accessTokenKey);  
		 cb.setJSONStoreEnabled(true);
		 
		String key = "fort mcmurray fire";
		String sinceDate = "2016-02-01";
//		String endDate = "2016-02-01";
		new PeriodSearch(cb).search(key, 100, sinceDate, null);
	}

}
