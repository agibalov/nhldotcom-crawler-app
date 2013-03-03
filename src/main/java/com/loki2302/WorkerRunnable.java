package com.loki2302;

import java.io.IOException;

public class WorkerRunnable implements Runnable {
	private final CrawlerContext crawlerContext;
	private final CrawlerTask task;
	
	public WorkerRunnable(CrawlerContext crawlerContext, CrawlerTask task) {
		this.crawlerContext = crawlerContext;
		this.task = task;
	}
	
	public void run() {			
		while(true) {
			try {
				task.Execute(crawlerContext);
				return;
			} catch (IOException e) {
				e.printStackTrace();
				try {
					Thread.sleep(1);
				} catch(InterruptedException e2) {						
				}
			}						
		}
	}    	
}