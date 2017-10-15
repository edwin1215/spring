package com.edwin.spring.web.zookeeper.common;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * 
 * @author Edwin
 *
 */
public class Session implements Watcher {

	@Override
	public void process(WatchedEvent event) {
		System.out.println("===================");
		System.out.println("Receive watched event:" + event);
		System.out.println(event.getState());
		System.out.println(event.getPath());
		System.out.println(event.getType());
		System.out.println("===================");

	}

	public static void main(String[] args) {

		try {
			ZooKeeper z = new ZooKeeper("192.168.1.105:2181", 5000,
					new Session());
			System.out.println(create(z, "/edwin", "jy"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String create(ZooKeeper z, String path, String data) {
		try {
			String create = z.create(path, data.getBytes(),
					Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			return create;
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void destory() {
		System.out.println("");
	}
}
