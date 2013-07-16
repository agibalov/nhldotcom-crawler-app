package com.loki2302;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ProcessPlayer implements CrawlerTask {
	private final String url;
	
	public ProcessPlayer(String url) {
		this.url = url;
	}
	
	public void execute(CrawlerContext crawlerContext) throws IOException {
		Document doc = Jsoup.connect(url).get();      	
		Element nameElement = doc.select("#tombstone h1 *").first();
    	String playerName = fixString(nameElement.ownText());    	
    	crawlerContext.addPlayerName(playerName);
	}
	
	private static String fixString(String s) {
		return s.replace(String.valueOf((char)160), " ").trim();
	}
}