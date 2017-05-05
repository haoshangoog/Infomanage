package com.hand.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hand.util.intTypeAdapter;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {
	public Map session;
	public HttpServletRequest request;
	public HttpServletResponse response;

	Gson gson = new GsonBuilder()
			.excludeFieldsWithoutExposeAnnotation()
			.registerTypeAdapter(int.class, new intTypeAdapter())
			.registerTypeAdapter(Integer.class, new intTypeAdapter())
			.create();

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		this.response.setHeader("Access-Control-Allow-Origin", "http://localhost:8082");
		this.response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		this.response.setHeader("Access-Control-Max-Age", "3600");
		this.response.setHeader("Access-Control-Allow-Headers", "Date, Set-Cookie, X-CSRF-TOKEN, Origin, X-Requested-With, Content-Type, Accept");
		this.response.setHeader("Access-Control-Expose-Headers", "Set-Cookie, Date, Access-Control-Allow-Methods");
		this.response.setHeader("Access-Control-Allow-Credentials","true");
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.session = map;
	}
}