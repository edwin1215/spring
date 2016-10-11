package com.edwin.spring.web.aop;

public class ForumServiceImpl implements ForumService {

	@SuppressWarnings("static-access")
	@Override
	public void removeTopic(int topicId) {
		try {
			Thread.currentThread().sleep(200);
			LOGGER.info("execute removeTopic.{}", topicId);
		} catch (InterruptedException e) {
			LOGGER.error("", e);
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void removeForum(int forumId) {
		try {
			Thread.currentThread().sleep(500);
			LOGGER.info("execute removeForum.{}", forumId);
		} catch (InterruptedException e) {
			LOGGER.error("", e);
		}
	}
}
