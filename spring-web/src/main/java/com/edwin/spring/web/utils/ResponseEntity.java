package com.edwin.spring.web.utils;

import java.io.Serializable;

public class ResponseEntity<T> implements Serializable {

	private static final long serialVersionUID = 5941191550867843598L;

	public String code = "0";

	public String msg;

	public T result;

	public ResponseEntity() {

	}

	public ResponseEntity(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ResponseEntity(String code, String msg, T result) {
		this(code, msg);
		this.result = result;
	}

	public ResponseEntity(StatsCodeEnum statsCode) {
		this(statsCode.getCode(), statsCode.getMsg());
	}

	public static ResponseEntity<?> buildSuccess() {
		return new ResponseEntity<Void>(StatsCodeEnum.SUCCESS);
	}

	public static <E> ResponseEntity<E> buildSuccess(E result) {
		ResponseEntity<E> responseEntity = new ResponseEntity<E>(
				StatsCodeEnum.SUCCESS);
		responseEntity.result = result;
		return responseEntity;
	}

	public static ResponseEntity<?> buildFail() {
		return new ResponseEntity<Void>(StatsCodeEnum.FAIL);
	}

	public static ResponseEntity<?> buildFail(StatsCodeEnum statsCode) {
		return new ResponseEntity<Void>(statsCode);
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public T getResult() {
		return result;
	}
}
