package com.wang.chap01;

import java.sql.Timestamp;
import java.util.Date;

import com.sun.corba.se.spi.orb.StringPair;

public class Url {

	//原始url，主机部分是域名
	private String oriUrl;
	//url的值，主机部分是ip，为了防止重复主机的出现。
	private String url;
	//url num
	private int urlNo;
	//url返回的结果码
	private int statusCode;
	//此url被其他文章引用的次数
	private int hitNo;
	//此url对应文章的汉字编码
	private String charSet;
	//文章摘要
	private String abstractText;
	//作者
	private String author;
	//权重
	private int weight;
	//文章的描述
	private String description;
	//文章大小
	private int fileSize;
	//最后修改时间
	private Timestamp lastUpdateTime;
	//过期时间
	private Date timeToLive;
	//文章名称
	private String title;
	//文章类型
	private String type;
	//引用的链接
	private String[] urlReferences;
	//爬取的层次，从种子开始，从零开始
	private int layer;
	
	public String getOriUrl() {
		return oriUrl;
	}
	public void setOriUrl(String oriUrl) {
		this.oriUrl = oriUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getUrlNo() {
		return urlNo;
	}
	public void setUrlNo(int urlNo) {
		this.urlNo = urlNo;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public int getHitNo() {
		return hitNo;
	}
	public void setHitNo(int hitNo) {
		this.hitNo = hitNo;
	}
	public String getCharSet() {
		return charSet;
	}
	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
	public String getAbstractText() {
		return abstractText;
	}
	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Date getTimeToLive() {
		return timeToLive;
	}
	public void setTimeToLive(Date timeToLive) {
		this.timeToLive = timeToLive;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String[] getUrlReferences() {
		return urlReferences;
	}
	public void setUrlReferences(String[] urlReferences) {
		this.urlReferences = urlReferences;
	}
	public int getLayer() {
		return layer;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	
}
