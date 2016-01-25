package com.wang.chap01;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


public class LinkQueue {

	/**
	 * visited url set
	 */
	private static Set<Object> visitedUrl = new HashSet<>();
	
	/**
	 * unvisited url queue
	 */
	private static Queue<Object> unVisitedUrl = new LinkedList();
	
	//获得url队列
	public static Queue<Object> getUnVisitedUrl() {
		return unVisitedUrl;
	}
	
	/**
	 * 添加url到访问过的队列中
	 * @param url
	 */
	public static void addVisitedUrl(String url) {
		visitedUrl.add(url);
	}
	
	/**
	 * remove visited url from visitedUrl
	 * @param url
	 * @return boolean
	 */
	public static boolean removeVisitedUrl(String url) {
		return visitedUrl.remove(url);
	}
	
	/**
	 * dequeue unvisited url
	 * @return
	 */
	public static Object unVisitedUrlDeQueue() {
		return unVisitedUrl.poll();
	}
	
	/**
	 * add unvisited url;
	 * @param url
	 */
	public static void addUnVisitedUrl(String url) {
		if (url != null && !url.trim().equals("") && !visitedUrl.contains(url) && !unVisitedUrl.contains(url)) {
			unVisitedUrl.add(url);
		}
	}
	
	/**
	 * return the number of visited url;
	 * @return
	 */
	public static int getVisitedUrlNum() {
		return visitedUrl.size();
	}
	
	/**
	 * is visited url empty?
	 * @return
	 */
	public static boolean unVisitedUrlIsEmpty() {
		return unVisitedUrl.isEmpty();
	}
}
