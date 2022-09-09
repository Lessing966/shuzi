package com.example.demo.common;

import java.util.HashMap;
import java.util.Map;


public class R extends HashMap<String, Object> {

	public R() {
		put("success",true);
		put("code", 0);
		put("msg", "接收成功");
	}

	public static R error(Boolean success,int code, String msg) {
		R r = new R();
		r.put("success",success);
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

}