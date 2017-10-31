package com.edwin.spring.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.edwin.spring.web.utils.Base64Utils;
import com.edwin.spring.web.utils.ClientInfo;
import com.edwin.spring.web.utils.CookieThreadLocal;
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

	// private final static String URL = "http://60.205.132.21:10002/";
	private final static String URL = "http://localhost/";

	// private static String priKey =
	// "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALxM270FGnSDNP/z2+naSoHFyo8V9l5DzyZiRO/XxkcfIgN0EiSNcvSz4qNn8dq/4/OX659ditA4lWxUsSdqZG3iG0ofigMDYBChkHEvq0sprNjj/EpNiZrz4novUoOS5BlSpUeLzMLxTBByw8vU5FJITNfE/I/IzFC801KndA2JAgMBAAECgYEAn61xfYXRXFJTZGVP7KwfGJM45UOTi3ZnOL6GhPjufCtLdbA9HmAQxq+wli80KfMlg9EljxwM9eu10oqzO6B+Djga0gbaHxJNWSZhMOGzp40KvyG9Q0GsVPDBs3kR01axssap1OdccMwdhrZcDb4hioEjR+bn7LB4ebKmvPLOj10CQQDcWyD5ltdSv8kIk2dHuKzMt6V0S1A63Nch+xl6hZ/PU/WnZoOnkL4dfr7pnTXa+0AUmfUBUvm/5q++k/ThXXhjAkEA2sJPsi1Gp16zeKQL86eaOAEGVAvruEDcsH2gu0MBO7KNLmGyiwHTXISjw9BsQom9LcC/CUscZYtkwT0PcuiIIwJBANQJoJ5rJsGKqNDmvnBGZYkMglp4ijhJ/33EWQ0L/e6MNQsjWzZn0nkhWGYGECeqs0vfpeHjTU36lkxFpVMjFU8CQQC/KFTj6dR3pLVHknIvNCH7FkolX1VX6LXwM5cki+Sj+d6MzSprdejwS6efEJ3Jdvss4+ULhesGgMxGX8kELFXpAkBH3zfR1TnLhusnVxD1NS0H5trK9T2o3nB9LI1gv/cVmo1eEhh4QNZlJtZ7eCzuPnrPHiChxxEXKlAunnLRTx4c";
	private static String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8TNu9BRp0gzT/89vp2kqBxcqPFfZeQ88mYkTv18ZHHyIDdBIkjXL0s+KjZ/Hav+Pzl+ufXYrQOJVsVLEnamRt4htKH4oDA2AQoZBxL6tLKazY4/xKTYma8+J6L1KDkuQZUqVHi8zC8UwQcsPL1ORSSEzXxPyPyMxQvNNSp3QNiQIDAQAB";
	private static String vKey = "YnVzb25saW5lLmNvbQ==";
	@Autowired
	private DispatcherService dispatcherService;

	@RequestMapping("code")
	public @ResponseBody Object testUrlCode(HttpServletRequest request,
			HttpServletResponse response, @RequestBody String jsonParam) {
		String key = request.getParameter("key");
		String gbkKey = null;
		String utf8Key = null;
		try {
			gbkKey = URLDecoder.decode(
					new String(key.getBytes("ISO-8859-1")), "GBK");
			utf8Key = URLDecoder.decode(
					new String(key.getBytes("ISO-8859-1")), "UTF-8");
			System.out.println(gbkKey);
			System.out.println(utf8Key);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gbkKey;
	}

	@RequestMapping("rsa")
	public @ResponseBody Object testRSA(HttpServletRequest request,
			HttpServletResponse response, @RequestBody String jsonParam) {

		LOGGER.info("jsonParam:{}", jsonParam);
		InputStream is = null;
		try {
			is = request.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String reqBody = sb.toString();
			LOGGER.info("阿里云截图回调.reqBody:{}", reqBody);
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Map<String, String> params = new HashMap<String, String>();
		String data = "1:operate:" + vKey;
		try {
			byte[] dateBytes = data.getBytes();
			String encode = Base64Utils.encode(RSAUtils.encryptByPublicKey(
					dateBytes, pubKey));
			// String urlEncode = URLEncoder.encode(str, "UTF-8");
			System.out.println(encode);
			params.put("token", encode);
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
		setHeader(response);
		return obj;
	}

	@RequestMapping("dispatch")
	public @ResponseBody Object dispatch() {
		Map<String, String> params = new HashMap<String, String>();
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

	private void setHeader(HttpServletResponse response) {
		Header[] cookies = CookieThreadLocal.get();
		if (cookies == null || cookies.length == 0) {
			return;
		}
		for (Header cookie : cookies) {
			response.addHeader(cookie.getName(), cookie.getValue());
		}
	}

	enum RequestType {
		GET("GET"), POST("POST");

		public String value;

		private RequestType(String value) {
			this.value = value;
		}
	}

	/**
	 * 生成md5
	 * 
	 * @param message
	 * @return
	 */
	public static String getMD5(String message) {
		String md5str = "";
		try {
			// 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 2 将消息变成byte数组
			byte[] input = message.getBytes();

			// 3 计算后获得字节数组,这就是那128位了
			byte[] buff = md.digest(input);

			// 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
			md5str = bytesToHex(buff);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5str;
	}

	/**
	 * 二进制转十六进制
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
		StringBuffer md5str = new StringBuffer();
		// 把数组每一字节换成16进制连成md5字符串
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			digital = bytes[i];

			if (digital < 0) {
				digital += 256;
			}
			if (digital < 16) {
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));
		}
		return md5str.toString().toUpperCase();
	}

	public static void main(String[] args) throws Exception {
		// String result = getMD5("aaa");
		// System.err.println(result);
		ThreadLocal<String> tl = new ThreadLocal<String>();
		tl.set("haha");
		ThreadLocal<String> tl1 = new ThreadLocal<String>();
		System.out.println(tl.get());
		System.out.println(tl.get());
		System.out.println(tl1.get());
	}
}
