package com.wang.chap01;

//经过实验，htmlparser对html的解析有问题，无法解析出所有的img标签，故需要舍弃这种用法。可能还是需要使用正则表达式。

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;

public class HtmlParserTool {

	public static Set<String> extractLinks(String url, LinkFilter filter) {
		Set<String> links = new HashSet<String>();
		try {
			Parser parser = new Parser(url);
			parser.setEncoding("gb2312");
			//过滤frame标签，提取frame中的src
			NodeFilter frameFilter = new NodeFilter() {
				
				@Override
				public boolean accept(Node arg0) {
					// TODO Auto-generated method stub
					if (arg0.getText().startsWith("frame src=")){
						return true;
					}
					return false;
				}
			};
			
			//OrFilter设置过滤<a>标签，和<frame>标签
			OrFilter linkFilter = new OrFilter(new NodeClassFilter(LinkTag.class), frameFilter);
			
			//得到所有经过过滤的标签
			NodeList list = parser.extractAllNodesThatMatch(linkFilter);
			for (int i = 0; i < list.size(); i++){
				Node tag = list.elementAt(i);
				if (tag instanceof LinkTag){
					LinkTag linkTag = (LinkTag)tag;
					String linkUrl = linkTag.getLink();
					if (filter.accept(linkUrl)){
						links.add(linkUrl);
					}
					
				}else {
					String frame = tag.getText();
					int start = frame.indexOf("src=");
					frame = frame.substring(start);
					int end = frame.indexOf(" ");
					if (end == -1){
						end = frame.indexOf(">");
					}
					String frameUrl = frame.substring(5, end-1);
					
					if (filter.accept(frameUrl)){
						links.add(frameUrl);
					}
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return links;
	}
	
	public static LinkedList<ImageTag> getImgTags(String url) {
		System.out.println("HtmlParserTool.getImgTags()");
		System.out.println(url);
		LinkedList<ImageTag> imgUrls = new LinkedList<ImageTag>();
		try{
			Parser parser = new Parser(url);
			parser.setEncoding("GBK");
			
			
			//按照标签名过滤
			NodeFilter filter = new TagNameFilter("img");
			//获得所有的img标签节点
			NodeList list = parser.extractAllNodesThatMatch(filter);
			System.out.println(list.size());
			for (int i = 0; i < list.size(); i++){
				ImageTag tag = (ImageTag)list.elementAt(i);
				imgUrls.add(tag);
				System.out.println("HtmlParserTool.getImgTags()");
				System.out.println(tag.getImageURL());
				System.out.println(tag.getText());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return imgUrls;
	}

	
}
