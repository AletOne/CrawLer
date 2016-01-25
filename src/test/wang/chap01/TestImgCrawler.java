package test.wang.chap01;

import com.wang.chap01.ImageCrawler;

public class TestImgCrawler {

	public static void main(String[] args) {
		ImageCrawler crawler = new ImageCrawler();
		crawler.crawling(new String[] {"http://www.hao123.com"});
	}
}
		