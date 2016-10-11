package com.edwin.spring.web.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务类
 * 
 * @author caojunming
 *
 */
public interface ForumService {

	public final static Logger LOGGER = LoggerFactory
			.getLogger(ForumService.class);

	public void removeTopic(int topicId);

	public void removeForum(int forumId);
}
