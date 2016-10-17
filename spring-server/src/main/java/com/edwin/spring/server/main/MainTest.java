package com.edwin.spring.server.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import com.edwin.spring.server.util.BicycleConstants;

public class MainTest {

	public final static Jedis JEDIS;

	static {
		JEDIS = new Jedis("172.17.16.190", 6379);
		JEDIS.auth("n6vHtRMsdAH6EBiY");
	}

	public static void main(String[] args) {

		System.out.println(JEDIS.zrange(
				BicycleConstants.ANCHOR_DAY_RANKING_ZSET, 0, -1));
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
