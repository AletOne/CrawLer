package com.wang.util;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class HtmlUtil {

	//img标签正则
	private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
	//imgsrc正则
	private static final String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)"; 
	
	public static String getHtml(String url){
		System.out.println("HtmlUtil.getHtml()");
		String html = null;
		//1.生成httpclient并设置参数
		HttpClient httpClient = new HttpClient();
		//设置http超时5秒
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
		//2.生成getmethod对象并设置参数
		GetMethod getMethod = new GetMethod(url);
		//设置超时5秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
		//设置请求重试处理
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

		//3.执行get请求
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK){
				System.err.println("Method failed: " + getMethod.getStatusCode());
				html = null;
			}
			
			//4.handle reponse
			InputStream inputStream = getMethod.getResponseBodyAsStream();
			StringBuffer  out = new StringBuffer(); 
	        byte[] b = new byte[4096]; 
	        for   (int n; (n = inputStream.read(b)) != -1;){ 
	                out.append(new String(b, 0, n)); 
	        } 
			html = out.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return html;
	}
	
	/**
	 * 匹配正则表达式
	 * @param source
	 * @param element
	 * @param attr
	 * @return
	 */
	
	public static Map<String, String> match(String source, String element, String attr){
		Map<String, String> result = new LinkedHashMap<>();
		//String reg = "<" + element + "[^<>]*?\\s"+ attr+ "=['\"]?(.*?)['\"]?\\s.*?>";
		//String reg = "<" + element + "[^<>]*?\\s"+ attr+ "=\"(.*?)\"\\s*/>";
		String reg = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
		Matcher matcher = Pattern.compile(reg).matcher(source);
		while (matcher.find()){
			String key = matcher.group(0);
			String value = matcher.group(1);
			result.put(key, value);
		}
		return result;
	}
	
	public static Set<String> getImgUrls(String html){		
		Set<String> imgSrcs = new HashSet<>();
		
		Map<String, String> map = match(html, "img", "src");
		Set<Entry<String, String>> set = map.entrySet();
		for (Iterator<Entry<String, String>> iterator = set.iterator(); iterator.hasNext();){
			Entry<String, String> entry = iterator.next();
			imgSrcs.add(entry.getValue());
		}
		return imgSrcs;
	}
	
	
//	public static void main(String[] args) {
//        String source =
//        "<img  title='w213213' src='http:\\\\www.qq.com/asd/sadsd+_#$' />";
//        Map map = match(source, "img", "src");
//        Set<Map.Entry> set = map.entrySet();
//           for (Iterator<Map.Entry> it = set.iterator(); it.hasNext();) {
//               Map.Entry entry = (Map.Entry) it.next();
//               System.out.println( entry.getValue());
//           }
//        
//   }
}
