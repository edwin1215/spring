package com.edwin.spring.web.controller;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.rmi.UnknownHostException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.edwin.spring.web.utils.ResponseEntity;

/**
 * 
 * @author caojunming
 * @date 2016-7-23
 *
 */
@Component
@Scope("singleton")
public class ThreadPoolHttpClient {

	private static Logger LOGGER = LoggerFactory
			.getLogger(ThreadPoolHttpClient.class);

	public static final String UNICODE_TYPE = "utf-8";

	private PoolingHttpClientConnectionManager cm;

	private CloseableHttpClient httpClient;

	/**
	 * init
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("ThreadPoolHttpClient init...");
		initManager();
		initClient();
	}

	/**
	 * 获取httpclient对象
	 * 
	 * @return
	 */
	public CloseableHttpClient getHttpClient() {
		if (httpClient != null) {
			return httpClient;
		}
		if (cm == null) {
			initManager();
		}
		initClient();
		return httpClient;

	}

	/**
	 * config
	 */
	private void config(HttpRequestBase httpRequestBase) {
		httpRequestBase.setHeader("User-Agent", "Mozilla/5.0");
		httpRequestBase
				.setHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequestBase.setHeader("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");// "en-US,en;q=0.5");
		httpRequestBase.setHeader("Accept-Charset",
				"ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7");

		// 配置请求的超时设置
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(3000).setConnectTimeout(3000)
				.setSocketTimeout(3000).build();
		httpRequestBase.setConfig(requestConfig);
	}

	/**
	 * init
	 */
	public void initManager() {
		cm = new PoolingHttpClientConnectionManager();
		// 将最大连接数增加到200
		cm.setMaxTotal(200);
		// 将每个路由基础的连接增加到20
		cm.setDefaultMaxPerRoute(20);
		// 将目标主机的最大连接数增加到50
		HttpHost localhost = new HttpHost("http://localhost", 80);
		cm.setMaxPerRoute(new HttpRoute(localhost), 50);
	}

	/**
	 * init
	 */
	public void initClient() {
		// 请求重试处理
		HttpRequestRetryHandler httpRequestRetryHandler = initRetryHandler();
		httpClient = HttpClients.custom().setConnectionManager(cm)
				.setRetryHandler(httpRequestRetryHandler).build();
	}

	/**
	 * 重试策略
	 * 
	 * @return
	 */
	public HttpRequestRetryHandler initRetryHandler() {
		return new HttpRequestRetryHandler() {
			public boolean retryRequest(IOException exception,
					int executionCount, HttpContext context) {
				if (executionCount >= 5) {// 如果已经重试了5次，就放弃
					return false;
				}
				if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
					return true;
				}
				if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
					return false;
				}
				if (exception instanceof InterruptedIOException) {// 超时
					return false;
				}
				if (exception instanceof UnknownHostException) {// 目标服务器不可达
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
					return false;
				}
				if (exception instanceof SSLException) {// ssl握手异常
					return false;
				}

				HttpClientContext clientContext = HttpClientContext
						.adapt(context);
				HttpRequest request = clientContext.getRequest();
				// 如果请求是幂等的，就再次尝试
				if (!(request instanceof HttpEntityEnclosingRequest)) {
					return true;
				}
				return false;
			}
		};
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 */
	public ResponseEntity<String> get(String url) {
		long start = System.currentTimeMillis();
		ResponseEntity<String> responseEntity = null;
		try {
			HttpGet httpget = new HttpGet(url);
			config(httpget);
			// 启动线程抓取
			CloseableHttpResponse response = null;
			LOGGER.info("GET dispatcher url: {}", url);
			response = httpClient.execute(httpget, HttpClientContext.create());
			HttpEntity entity = response.getEntity();
			String responseStr = EntityUtils.toString(entity, UNICODE_TYPE);
			responseEntity = ResponseEntity.buildSuccess(responseStr);
			LOGGER.info("GET dispatcher return: {}", responseStr);
			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("httpclient send request get time -> "
				+ (end - start));
		return responseEntity;
	}

	/**
	 * post请求
	 * 
	 * @param uri
	 * @return
	 */
	public ResponseEntity<String> post(URI uri, List<NameValuePair> params) {
		long start = System.currentTimeMillis();
		ResponseEntity<String> responseEntity = null;
		try {
			HttpPost httpPost = new HttpPost(uri);
			if (params != null && !params.isEmpty()) {
				httpPost.setEntity(new UrlEncodedFormEntity(params));
			}
			config(httpPost);
			// 启动线程抓取
			CloseableHttpResponse response = null;
			response = httpClient.execute(httpPost, HttpClientContext.create());
			HttpEntity entity = response.getEntity();
			String responseStr = EntityUtils.toString(entity, UNICODE_TYPE);
			responseEntity = ResponseEntity.buildSuccess(responseStr);
			LOGGER.info("post dispatcher: {}", responseStr);
			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("httpclient send request post time -> "
				+ (end - start));
		return responseEntity;
	}

}
