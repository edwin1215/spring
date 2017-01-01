package com.edwin.spring.web.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edwin.spring.web.utils.ClientInfo;
import com.edwin.spring.web.utils.ResponseEntity;

/**
 * 
 * @author caojunming
 * @date 2016-7-23
 *
 */
@Service
public class DispatcherService {
	private static Logger LOGGER = LoggerFactory
			.getLogger(DispatcherService.class);
	@Autowired
	private ThreadPoolHttpClient threadPoolHttpClient;

	public ResponseEntity<String> dispatcher(ClientInfo clientInfo)
			throws URISyntaxException {
		ResponseEntity<String> responseEntity = null;
		if (clientInfo.getRequestType() == null) {
			clientInfo.setRequestType("GET");
		}
		if (clientInfo.getRequestType().toUpperCase().equals("GET")) {
			buildGetParams(clientInfo);
			responseEntity = threadPoolHttpClient.get(clientInfo.getUrl()
					.toString());
		} else if (clientInfo.getRequestType().toUpperCase().equals("POST")) {
			responseEntity = threadPoolHttpClient.post(new URI(clientInfo
					.getUrl().toString()), buildPostParams(clientInfo));
		} else {
			LOGGER.error("request type is error:{}",
					clientInfo.getRequestType());
		}
		return responseEntity;
	}

	private void buildGetParams(ClientInfo clientInfo) {
		if (clientInfo == null || clientInfo.getParams() == null) {
			return;
		}

		for (String key : clientInfo.getParams().keySet()) {
			if (StringUtils.isNotBlank(clientInfo.getParams().get(key))) {
				clientInfo.getUrl().append("&").append(key).append("=")
						.append(clientInfo.getParams().get(key));
			}
		}

		if (clientInfo.getUrl().toString().indexOf("?") < 0
				&& clientInfo.getUrl().toString().indexOf("&") >= 0) {
			int index = clientInfo.getUrl().indexOf("&");
			clientInfo.getUrl().replace(index, index + 1, "?");
		}
	}

	private List<NameValuePair> buildPostParams(ClientInfo clientInfo) {
		if (clientInfo == null || clientInfo.getParams() == null) {
			return null;
		}
		List<NameValuePair> result = new ArrayList<NameValuePair>();
		for (String key : clientInfo.getParams().keySet()) {
			if (StringUtils.isNotBlank(clientInfo.getParams().get(key))) {
				result.add(new BasicNameValuePair(key, clientInfo.getParams()
						.get(key)));
			}
		}
		return result;
	}

	public static void main(String[] args) {
		StringBuffer s = new StringBuffer("http://localhost/nuser/getAllData");
		Map<String, String> m = new HashMap<String, String>();
		m.put("data", "2016");
		m.put("channel", "微信");
		m.put("platform", null);

		for (String key : m.keySet()) {
			if (StringUtils.isNotBlank(m.get(key))) {
				s.append("&").append(key).append("=").append(m.get(key));
			}
		}
		System.out.println(s.toString());
		if (s.indexOf("?") < 0) {
			s.replace(s.indexOf("&"), s.indexOf("&") + 1, "?");
		}
		System.out.println(s.toString());
	}
}
