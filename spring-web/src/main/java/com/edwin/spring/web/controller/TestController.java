package com.edwin.spring.web.controller;

import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.edwin.spring.web.utils.Base64Utils;
import com.edwin.spring.web.utils.ClientInfo;
import com.edwin.spring.web.utils.RSAUtils;
import com.edwin.spring.web.utils.ResponseEntity;

/**
 * @author caojunming
 */
@Controller
@RequestMapping("test")
public class TestController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestController.class);

	private final static String URL = "http://60.205.132.21:10002/";
	// private final static String URL = "http://106.2.223.66:1936/";

	private static String priKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALxM270FGnSDNP/z2+naSoHFyo8V9l5DzyZiRO/XxkcfIgN0EiSNcvSz4qNn8dq/4/OX659ditA4lWxUsSdqZG3iG0ofigMDYBChkHEvq0sprNjj/EpNiZrz4novUoOS5BlSpUeLzMLxTBByw8vU5FJITNfE/I/IzFC801KndA2JAgMBAAECgYEAn61xfYXRXFJTZGVP7KwfGJM45UOTi3ZnOL6GhPjufCtLdbA9HmAQxq+wli80KfMlg9EljxwM9eu10oqzO6B+Djga0gbaHxJNWSZhMOGzp40KvyG9Q0GsVPDBs3kR01axssap1OdccMwdhrZcDb4hioEjR+bn7LB4ebKmvPLOj10CQQDcWyD5ltdSv8kIk2dHuKzMt6V0S1A63Nch+xl6hZ/PU/WnZoOnkL4dfr7pnTXa+0AUmfUBUvm/5q++k/ThXXhjAkEA2sJPsi1Gp16zeKQL86eaOAEGVAvruEDcsH2gu0MBO7KNLmGyiwHTXISjw9BsQom9LcC/CUscZYtkwT0PcuiIIwJBANQJoJ5rJsGKqNDmvnBGZYkMglp4ijhJ/33EWQ0L/e6MNQsjWzZn0nkhWGYGECeqs0vfpeHjTU36lkxFpVMjFU8CQQC/KFTj6dR3pLVHknIvNCH7FkolX1VX6LXwM5cki+Sj+d6MzSprdejwS6efEJ3Jdvss4+ULhesGgMxGX8kELFXpAkBH3zfR1TnLhusnVxD1NS0H5trK9T2o3nB9LI1gv/cVmo1eEhh4QNZlJtZ7eCzuPnrPHiChxxEXKlAunnLRTx4c";
	private static String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8TNu9BRp0gzT/89vp2kqBxcqPFfZeQ88mYkTv18ZHHyIDdBIkjXL0s+KjZ/Hav+Pzl+ufXYrQOJVsVLEnamRt4htKH4oDA2AQoZBxL6tLKazY4/xKTYma8+J6L1KDkuQZUqVHi8zC8UwQcsPL1ORSSEzXxPyPyMxQvNNSp3QNiQIDAQAB";
	private static String vKey = "YnVzb25saW5lLmNvbQ==";
	@Autowired
	private DispatcherService dispatcherService;

	@RequestMapping("rsa")
	public @ResponseBody Object testRSA() {
		Map<String, String> params = new HashMap<String, String>();
		String data = "1:operate:" + vKey;
		try {
			byte[] dateBytes = data.getBytes();
			byte[] encryptBytes = RSAUtils
					.encryptByPublicKey(dateBytes, pubKey);
			String str = new String(encryptBytes, "ISO-8859-1");
			String urlEncode = URLEncoder.encode(str, "UTF-8");
			params.put("token", urlEncode);
		} catch (Exception e) {
			LOGGER.error("encryptByPublicKey error.", e);
		}

		params.put("userId", "1");
		params.put("channel", "1");
		ClientInfo clientInfo = new ClientInfo(RequestType.POST.value,
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

	enum RequestType {
		GET("GET"), POST("POST");

		public String value;

		private RequestType(String value) {
			this.value = value;
		}
	}

	public static void main(String[] args) throws Exception {
		String data = "12:operate";
		byte[] bytes = RSAUtils.encryptByPublicKey(data.getBytes(), pubKey);
		String encodedData = new String(bytes, "ISO-8859-1");
		String encodeStr = Base64Utils.encode(bytes);
		System.out.println(encodeStr);
		System.out.println(encodedData);
		byte[] decodedDatas = RSAUtils.decryptByPrivateKey(
				Base64Utils.decode(encodeStr), priKey);
		String str = new String(decodedDatas);
		System.out.println(str);

	}
}
