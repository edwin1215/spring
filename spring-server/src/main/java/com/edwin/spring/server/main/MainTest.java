package com.edwin.spring.server.main;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public class MainTest {

	public final static Jedis JEDIS;

	static {
		JEDIS = new Jedis("192.168.108.151", 6379);
		// JEDIS.auth("n6vHtRMsdAH6EBiY");
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {

		KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
		instance.initialize(1024);
		KeyPair keyPair = instance.generateKeyPair();
		RSAPrivateKey priKey = (RSAPrivateKey) keyPair.getPrivate();
		RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
		System.out.println(priKey);
		System.out.println(pubKey);
		String encryptedStr = "sdfds23fd23";
		byte[] decrypt = decrypt(priKey, encryptedStr.getBytes());
		System.out.println(new String(decrypt));
	}

	protected static byte[] decrypt(RSAPrivateKey privateKey, byte[] obj) {
		if (privateKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.DECRYPT_MODE, privateKey);
				return cipher.doFinal(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static List<Response<Long>> plZRank() {
		List<String> list = new ArrayList<String>();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		list.add("dd");
		list.add("ee");

		List<Response<Long>> result = new ArrayList<Response<Long>>();

		Pipeline pipeline = JEDIS.pipelined();
		try {
			for (String s : list) {
				Response<Long> zrevrank = pipeline.zrevrank("myset", s);
				result.add(zrevrank);
			}
		} finally {
			JEDIS.resetState();
		}

		return result;
	}

	public static void zadd() {
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("aa", 1.0);
		map.put("bb", 2.0);
		map.put("cc", 3.0);
		map.put("dd", 4.0);
		map.put("ee", 5.0);
		JEDIS.zadd("myset", map);
	}

	public static void tests() {
		Long l = null;
		System.out.println(String.valueOf(l));
	}
}
