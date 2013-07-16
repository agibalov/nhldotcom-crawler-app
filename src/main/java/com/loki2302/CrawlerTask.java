package com.loki2302;

import java.io.IOException;

public interface CrawlerTask {
	void execute(CrawlerContext crawlerContext) throws IOException;
}