package com.edwin.spring.web.ioc.java.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.edwin.spring.web.ioc.java.pojo.LoginDao;
import com.edwin.spring.web.ioc.java.pojo.UserDao;

@Configuration
public class DaoConfig {

	@Bean
	public LoginDao loginDao() {
		return new LoginDao();
	}

	@Bean
	public UserDao userDao() {
		return new UserDao();
	}
}
