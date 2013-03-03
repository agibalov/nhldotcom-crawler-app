package com.loki2302;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {    	
    	ThreadPoolExecutor executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(80);
    	CrawlerContext context = new CrawlerContext(executorService);
    	context.submitTask(new ProcessAbc("http://www.nhl.com/ice/playersearch.htm"));
    	while(true) {
    		synchronized(executorService) {
    			int taskCount = 
    					executorService.getQueue().size() + 
    					executorService.getActiveCount(); 
    			if(taskCount == 0) {
    				break;
    			}
    		}
    		
    		Thread.sleep(1000);
    	}
    	
    	executorService.shutdown();
    }
}
