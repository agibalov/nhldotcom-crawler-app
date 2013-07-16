package com.loki2302;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {    	
    	CrawlerContext context = new CrawlerContext();
    	context.submitTask(new ProcessAbc("http://www.nhl.com/ice/playersearch.htm"));
    	
    	long startTime = System.currentTimeMillis();
    	
    	int numberOfThreads = 80;
    	List<Thread> workerThreads = new ArrayList<Thread>();
    	for(int i = 0; i < numberOfThreads; ++i) {
    	    Thread thread = new Thread(new WorkerRunnable(context));
    	    thread.start();
    	    workerThreads.add(thread);
    	}
    	
    	for(Thread thread : workerThreads) {
    	    thread.join();
    	}
    	
    	long duration = System.currentTimeMillis() - startTime;
    	System.out.printf("Finished in %d seconds\n", duration / 1000);
    }
}
