package com.wang.chap01;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.htmlparser.tags.ImageTag;

import com.sun.corba.se.spi.orbutil.threadpool.NoSuchWorkQueueException;
import com.wang.util.HtmlUtil;

public class ImageCrawler {

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
				if (url.startsWith("http")){
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
			
			DownloadFile downloadFile  = new DownloadFile();
			//获取url的内容
			String content = HtmlUtil.getHtml(visitUrl);
			System.out.println(content);
			
			//获取图片链接
			Set<String> imgUrls = HtmlUtil.getImgUrls(content);
			
			
			for (String img : imgUrls){
				System.out.println("------Print img url-------");
				if (!img.startsWith("http")){
					img = visitUrl + img;
				}
				
				if (img != null && img.startsWith("http") && !img.equals("")){
					downloadFile.downloadFile(img, "img/");
				}
				
			}
			
//			DownloadFile downloadFile = new DownloadFile();
//			
//			//先将url的网页文件内容下载到本地，得到本地的文件url
//			String parsedUrl = downloadFile.downloadFile(visitUrl, "temp/");
//			
//			//解析本地的文件
//			//测试能否得到动态生成的网页内容
//			LinkedList<ImageTag> tags = HtmlParserTool.getImgTags(parsedUrl);
//			for (ImageTag tag : tags){
//				System.out.println("---Print image url---");
//				String url = tag.getImageURL();
//				System.out.println(url);
//				if (!url.startsWith("http")){
//					url = visitUrl+url;
//				}
//				if (url != null && url.startsWith("http") && !url.equals("")){
//					downloadFile.downloadFile(url,"img/");
//				}
//			}
			
			
			
			
			
			
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
