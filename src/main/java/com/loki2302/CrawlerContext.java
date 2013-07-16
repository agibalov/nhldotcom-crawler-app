package com.loki2302;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class CrawlerContext {
    private final Queue<CrawlerTask> tasks = new LinkedBlockingQueue<CrawlerTask>();
    private final Set<CrawlerTask> pendingTasks = new HashSet<CrawlerTask>();    
    private final List<String> players = new ArrayList<String>();
	
	public void submitTask(CrawlerTask task) {
	    synchronized(this) {
	        tasks.add(task);
	    }
	}
	
	public CrawlerTask getTask() {
	    synchronized(this) {
	        CrawlerTask task = tasks.poll();
	        if(task == null) {
	            return null;
	        }
	        
	        pendingTasks.add(task);
	        return task;
	    }
	}
	
	public void finishTask(CrawlerTask task) {
	    synchronized(this) {
	        pendingTasks.remove(task);
	    }
	}
	
	public boolean shouldStop() {
	    synchronized(this) {
	        return tasks.isEmpty() && pendingTasks.isEmpty();
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