package com.wang.chap01;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.wang.util.IOUtil;

public class DownloadFile {

	/**
	 * 根据URL和网页类型生成需要保存的网页的文件名，去掉url中非文件字符
	 * @param url
	 * @param contentType
	 * @return
	 */
	public String getFileNameByUrl(String url, String contentType) {
		//remove "http://"
		url = url.substring(7);
		//text/html类型
		if (contentType.indexOf("html") != -1){
			url = url.replaceAll("[\\?/:*/<>\"]", "_") + ".html";
			return url;
		}
		//如application/pdf类型
		else{
			return url.replaceAll("[\\?/:*/<>\"]", "_");
//					+ "." + 
//					contentType.substring(contentType.lastIndexOf("/") + 1);
		}
	}
	
	/**
	 * 保存网页，filePath为保存路径
	 * @param data
	 * @param filePath
	 */
	private void saveToLocal(byte[] data, String filePath) {
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filePath)));
			for (int i = 0; i < data.length; i++){
				out.write(data[i]);
				
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public String downloadFile(String url, String name) {
		System.out.println("DownloadFile.downloadFile()");
		System.out.println(url);
		String filePath = null;
		//1.生成httpclient并设置参数
		HttpClient httpClient = new HttpClient();
		//设置http超时5秒
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
		//2.生成getmethod对象并设置参数
		GetMethod getMethod;
		try {
			getMethod = new GetMethod(url);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		//设置超时5秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
		//设置请求重试处理
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		//3.执行http get请求
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK){
				System.err.println("Method failed: " + getMethod.getStatusCode());
				filePath = null;
			}
			
			//4.处理http响应内容
			//byte[] responseBody = getMethod.getResponseBody();
			InputStream inputStream = getMethod.getResponseBodyAsStream();
//			BufferedReader reader =  new BufferedReader(new InputStreamReader(inputStream));
//			StringBuffer sb = new StringBuffer();
//			String result = "";
//			while ((result = reader.readLine()) != null){
//				sb.append(result);
//			}
			
			filePath = name + getFileNameByUrl(url, getMethod.getResponseHeader("Content-Type").getValue());
			saveToLocal(IOUtil.toByteArray(inputStream), filePath);
		} catch (HttpException e) {
			// TODO: handle exception
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return filePath;
	}
	
	

}
