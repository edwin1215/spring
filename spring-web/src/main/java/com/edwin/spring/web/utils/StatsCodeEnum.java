package com.edwin.spring.web.utils;

/**
 * 
 * @author caojunming
 * @date 2016-7-23
 *
 */
public enum StatsCodeEnum {

	SUCCESS("0", "success"), FAIL("-1", "fail"), PARAM_ERROR("101",
			"param_error"), SOCKET_TIMEOUT("601", "socket_timeout"), CONNECTION_TIMEOUT(
			"602", "connection_timeout");

	private String code;

	private String msg;

	private StatsCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
