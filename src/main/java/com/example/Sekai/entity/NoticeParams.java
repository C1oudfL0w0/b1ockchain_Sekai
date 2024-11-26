package com.example.Sekai.entity;

import com.google.gson.Gson;

import java.io.Serializable;

public class NoticeParams implements Serializable{
	public NoticeParams() {};
	public NoticeParams(String bn, String ip, String tradeName) {
		super();
		this.bn = bn;
		this.ip = ip;
		this.tradeName=tradeName;
	}

	private String bn;
	
	private String ip;

	private String tradeName;
	
	
	public String getBn() {
		return bn;
	}

	public void setBn(String bn) {
		this.bn = bn;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String toJSONString() {
		return new Gson().toJson(this);
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	
}
