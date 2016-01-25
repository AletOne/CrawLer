package com.wang.chap01;

import java.util.LinkedList;

/**
 * 队列，保存访问的url
 */
public class Queue {

	private LinkedList<Object> queue = new LinkedList<Object>();
	
	public void enQueue(Object t) {
		queue.add(t);
	}
	
	public Object deQueue() {
		return queue.removeFirst();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public boolean constains(Object t) {
		return queue.contains(t);
	}
}
