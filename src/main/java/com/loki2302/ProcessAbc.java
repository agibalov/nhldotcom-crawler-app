package com.loki2302;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProcessAbc implements CrawlerTask {
	private final String url;
	
	public ProcessAbc(String url) {
		this.url = url;
	}
	
	public void execute(CrawlerContext crawlerContext) throws IOException {
		Document doc = Jsoup.connect(url).get();
    	Elements elements = doc.select("#playerSearch > .lastInitial > a");
    	for(Element e : elements) {
    		crawlerContext.submitTask(new ProcessLetter(e.attr("abs:href"), true));
    	}
	}
}