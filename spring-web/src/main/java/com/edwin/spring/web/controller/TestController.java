package com.edwin.spring.web.controller;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.edwin.spring.web.utils.ClientInfo;
import com.edwin.spring.web.utils.RSAUtils;
import com.edwin.spring.web.utils.ResponseEntity;

/**
 * 
 * @author caojunming
 *
 */
@Controller
@RequestMapping("test")
public class TestController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestController.class);

	private final static String URL = "http://localhost/";

	@Autowired
	private DispatcherService dispatcherService;

	@RequestMapping("rsa")
	public @ResponseBody Object testRSA() {
		Map<String, String> params = new HashMap<String, String>();
		String data = "12:operate";
		String pk = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSQytzk/Cj3ipRxJ+nfwz27va3u6eRrlcQsSEOtn8P6jVNJXGT17sDu+ab5OjBrHSE6HoTDe5GGCBNaZpYAosc7iIlN1DcLbOUCcvnqvb9uJBGDAYNlDCDwUNXz2YMyVjObN+j8kKW/2AJ9pTGemwPF79/huZDIouChFKvbUPGQwIDAQAB";
		try {
			params.put(
					"token",
					new String(RSAUtils.encryptByPublicKey(data.getBytes(), pk)));
		} catch (Exception e) {
			LOGGER.error("encryptByPublicKey error.", e);
		}
		params.put("channel", "1");
		ClientInfo clientInfo = new ClientInfo(RequestType.GET.value,
				"cms/third/check", URL);
		clientInfo.setParams(params);
		JSONObject obj = null;
		try {
			ResponseEntity<String> entity = dispatcherService
					.dispatcher(clientInfo);
			obj = JSONObject.parseObject(entity.getResult());
		} catch (URISyntaxException e) {
			LOGGER.error("dispatcher error: ", e);
		}
		LOGGER.info("return : {}", obj);
		return obj;
	}

	static enum RequestType {
		GET("GET"), POST("POST");

		public String value;

		private RequestType(String value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {

	}
}
