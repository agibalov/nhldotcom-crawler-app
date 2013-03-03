package com.loki2302;

import java.io.IOException;

public interface CrawlerTask {
	void Execute(CrawlerContext crawlerContext) throws IOException;
}