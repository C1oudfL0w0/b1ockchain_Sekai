package com.example.Sekai.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * 用于从pendding表中取出交易信息后，转为此对象
 *
 */
public class TradeBodyPool implements Serializable{
	/**
	 * tok交易信息
	 * @return
	 */
	Map<String, TradeObject> tbMap;

	
	
			
	public TradeBodyPool() {
		super();
		tbMap = new HashMap<String, TradeObject>();

	}

	public Map<String, TradeObject> getTbMap() {
		return tbMap;
	}

	public void setTbMap(Map<String, TradeObject> tbMap) {
		this.tbMap = tbMap;
	}
}
