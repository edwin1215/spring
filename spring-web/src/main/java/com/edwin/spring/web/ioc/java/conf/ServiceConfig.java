package com.edwin.spring.web.ioc.java.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.edwin.spring.web.ioc.java.pojo.LoginDao;
import com.edwin.spring.web.ioc.java.pojo.LoginService;
import com.edwin.spring.web.ioc.java.pojo.UserDao;

@Configuration
@Import(DaoConfig.class)
// @ImportResource("classpath:spring-application.xml")
public class ServiceConfig {

	@Bean
	@Autowired
	public LoginService loginService(LoginDao loginDao, UserDao userDao) {
		LoginService loginService = new LoginService();
		loginService.setLoginDao(loginDao);
		loginService.setUserDao(userDao);
		return loginService;
	}
}
