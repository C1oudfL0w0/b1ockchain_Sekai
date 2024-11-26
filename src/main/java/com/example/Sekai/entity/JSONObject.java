package com.example.Sekai.entity;

import com.google.gson.Gson;

public class JSONObject {
	private String code;
	private String msg;
	
	private Object o;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getO() {
		return o;
	}

	public void setO(Object o) {
		this.o = o;
	}
	
	
}
