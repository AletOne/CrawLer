package test.wang.chap01;

import com.wang.chap01.MyCrawler;

public class TestCrawler {

	public static void main(String[] args) {
		MyCrawler crawler = new MyCrawler();
		crawler.crawling(new String[] {"http://www.hao23.com"});
	}
}
