package com.df.support.utils;

import net.sf.json.JSONObject;

public class DwzRes{
	private JSONObject jo;
	public static final String OK = "200";
	public static final String ERROR = "300";
	public static final String TIMEOUT = "301";
	public static final String CLOSE = "closeCurrent";
	public static final String NOT_CLOSE = null;
	private String r = OK;
	private String msg;
	private Object data;
	private String navTabId; // 自动刷新
	private String rel;
	private String callbackType = CLOSE;
	private String forwardUrl;
	
	
	private DwzRes(){
		jo = new JSONObject();
	}
	
	public DwzRes put(Object key, Object value){
		jo.put(key, value);
		return this;
	}
	
	public static DwzRes newInstance(){
		DwzRes res = new DwzRes();
		res.put("statusCode", res.r);
		res.put("message", res.msg);
		res.put("callbackType", res.callbackType);
		return res;
	}
	
	public DwzRes r(String r){
		this.r = r;
		jo.put("statusCode", r);
		return this;
	}
	
	public DwzRes msg(String msg){
		this.msg = msg;
		jo.put("message", msg);
		return this;
	}
	public DwzRes navTabId(String navTabId){
		this.navTabId = navTabId;
		jo.put("navTabId", navTabId);
		return this;
	}
	public DwzRes callbackType(String callbackType){
		this.callbackType = callbackType;
		jo.put("callbackType", callbackType);
		return this;
	}
	public DwzRes data(Object data){
		this.data = data;
		jo.put("data", data);
		return this;
	}
	
	public String toJSON(){
		return jo.toString();
	}
	
	public String toString(){
		return toJSON();
	}

}
