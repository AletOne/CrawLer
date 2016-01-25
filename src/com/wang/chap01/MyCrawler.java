package com.wang.chap01;

import java.util.Set;

public class MyCrawler {

	/**
	 * 使用种子初始化URL队列
	 * @param seeds String[]
	 */
	private void initCrawlerWithSeeds(String[] seeds) {
		for(int i = 0; i < seeds.length; i++){
			LinkQueue.addUnVisitedUrl(seeds[i]);
		}
	}
	
	public void crawling(String[] seeds) {
		LinkFilter filter = new LinkFilter() {
			
			@Override
			public boolean accept(String url) {
				// TODO Auto-generated method stub
				if (url.startsWith("http://")){
					return true;
				}
				return false;
			}
		};
		
		initCrawlerWithSeeds(seeds);
		int count = 0;
		while (!LinkQueue.unVisitedUrlIsEmpty() && count <= 1000) {
			String visitUrl = (String) LinkQueue.unVisitedUrlDeQueue();
			if (visitUrl == null){
				continue;
			}
			DownloadFile downloadFile = new DownloadFile();
			downloadFile.downloadFile(visitUrl);
			LinkQueue.addVisitedUrl(visitUrl);
			
			Set<String> links = HtmlParserTool.extractLinks(visitUrl, filter);
			for (String link : links){
				System.out.println("print links");
				System.out.println(link);
				LinkQueue.addUnVisitedUrl(link);
			}
		}
	}
}
