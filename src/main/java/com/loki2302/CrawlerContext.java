package com.loki2302;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class CrawlerContext {    	
	public List<String> players = new ArrayList<String>();
	public ExecutorService executorService;
	
	public CrawlerContext(ExecutorService executorService) {
		this.executorService = executorService;
	}
	
	public void submitTask(CrawlerTask task) {
		synchronized(executorService) {
			executorService.submit(new WorkerRunnable(this, task));
		}
	}
	    	
	public void addPlayerName(String playerName) {
		synchronized(players) {
			players.add(playerName);
			System.out.printf(
					"Got player '%s' [%d]\n", 
					playerName, 
					players.size());
		}
	}
}