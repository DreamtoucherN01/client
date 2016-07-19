package com.blake.client;

public class Spider {
	
	public static void main(String args[]){
		
//		Thread stream = new Thread(new SpiderStreamChannel());
//		stream.start();
		
		
		Thread topic = new Thread(new SpiderTopicChannel());
		topic.start();
	}

}
