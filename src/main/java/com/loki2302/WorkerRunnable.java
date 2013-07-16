package com.loki2302;

import java.io.IOException;

public class WorkerRunnable implements Runnable {
    private final CrawlerContext context;
    
    public WorkerRunnable(CrawlerContext context) {
        this.context = context;
    }

    public void run() {
        while(!context.shouldStop()) {
            CrawlerTask task = context.getTask();
            if(task == null) {
                continue;
            }
            
            while(true) {
                try {
                    task.execute(context);
                    break;
                } catch (IOException e) {                        
                }
            }
            
            context.finishTask(task);
        }
    }
}