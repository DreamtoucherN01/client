package com.blake.search;

import java.util.List;

import twitter4j.Status;

public interface Search {
	
	public <T> List<T> search(String key);

	public <T> List<T> search(String key, int count, String sinceDate);

	public <T> List<T> search(String key, int count, String sinceDate, String endDate) ;

	public List<String> res2String(List<Status> tweets);

	public <T> List<T> search(String key, int count);

}
