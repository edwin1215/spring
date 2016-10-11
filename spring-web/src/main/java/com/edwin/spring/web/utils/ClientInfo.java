package com.edwin.spring.web.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author caojunming
 * @date 2016-7-23
 *
 */
public class ClientInfo {
	private String requestType;
	private String functionId;
	private String body;
	private StringBuffer url = new StringBuffer();
	private Map<String, String> params = new HashMap<String, String>();

	public ClientInfo(String requestType, String functionId, String url) {
		this.requestType = requestType;
		this.functionId = functionId;
		this.url.append(url).append(functionId);
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public StringBuffer getUrl() {
		return url;
	}

	public void setUrl(StringBuffer url) {
		this.url = url;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
