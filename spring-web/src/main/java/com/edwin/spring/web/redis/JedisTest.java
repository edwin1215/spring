package com.edwin.spring.web.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public class JedisTest {

	public final static Jedis JEDIS;

	static {
		JEDIS = new Jedis("192.168.108.156", 6379);
	}

	public static void main(String[] args) {
		// jedis.set("num", "12");
		// JEDIS.incr("num");
		// System.out.println(JEDIS.get("num"));

		// zadd();
		System.out.println(JEDIS.zcard("myset"));
		System.out.println(JEDIS.zrange("myset", 0, -1));
		System.out.println(JEDIS.zrevrange("myset", 0, -1));
		System.out.println(JEDIS.zrevrank("myset", "dd"));
		System.out.println("===================");
		List<Response<Long>> list = plZRank();
		for (Response<Long> o : list) {
			System.out.println(o.get());
		}
		System.out.println("===================");
		tests();
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
