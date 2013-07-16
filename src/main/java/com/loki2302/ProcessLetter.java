package com.loki2302;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProcessLetter implements CrawlerTask {
	private final String url;
	private boolean processPagination;
	
	public ProcessLetter(String url, boolean processPagination) {
		this.url = url;
		this.processPagination = processPagination;
	}
	
	public void execute(CrawlerContext crawlerContext) throws IOException {
		Document doc = Jsoup.connect(url).get();
    	Elements elements = doc.select("table.data > tbody > tr a[href*=player]");
    	for(Element e : elements) {
    		crawlerContext.submitTask(new ProcessPlayer(e.attr("abs:href")));
    	}
    	
    	if(!processPagination) {
    		return;
    	}
    	
    	elements = doc.select(".pageNumbers > a");
    	Set<String> urls = new HashSet<String>();
    	for(Element e : elements) {
    		urls.add(e.attr("abs:href"));        		
    	}
    	
    	for(String url : urls) {
    		crawlerContext.submitTask(new ProcessLetter(url, false));
    	}
	}
}