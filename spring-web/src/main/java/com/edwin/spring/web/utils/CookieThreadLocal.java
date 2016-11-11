package com.edwin.spring.web.utils;

import org.apache.http.Header;

public class CookieThreadLocal {

	private static ThreadLocal<Header[]> localCookies = new ThreadLocal<Header[]>();

	public static Header[] get() {
		return localCookies.get();
	}

	public static void set(Header[] header) {
		localCookies.set(header);
	}

	public static void clean() {
		localCookies.remove();
	}
}
