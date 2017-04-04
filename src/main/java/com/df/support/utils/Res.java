package com.df.support.utils;

import java.util.Collection;

import net.sf.json.JSONObject;

public class Res{
	private JSONObject jo;
	public static final int OK = 0;
	public static final int ERROR = 1;
	private int r;
	private String msg;
	private Object data;
	
	
	private Res(){
		jo = new JSONObject();
	}
	
	public Res put(Object key, Object value){
		jo.put(key, value);
		return this;
	}
	
	public static Res newInstance(){
		Res res = new Res();
		res.put("r", res.r);
		res.put("msg", res.msg);
		return res;
	}
	
	public Res r(int r){
		this.r = r;
		jo.put("r", r);
		return this;
	}
	
	public Res msg(String msg){
		this.msg = msg;
		jo.put("msg", msg);
		return this;
	}
	public Res data(Object data){
		this.data = data;
		jo.put("data", data);
		return this;
	}
	
	public String toJSON(){
		return jo.toString();
	}

	public int getR() {
		return r;
	}

	public String getMsg() {
		return msg;
	}

}
