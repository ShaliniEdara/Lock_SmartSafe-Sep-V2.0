package com.safesmart.safesmart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.iicorp.securam.config.AppConfig;
import com.iicorp.securam.datalink.auth.DefaultCredentials;
import com.iicorp.securam.demo.config.DefaultSecrets;
import com.iicorp.securam.demo.config.DemoControllerInterface;
import com.iicorp.securam.lock.api.LockController;
import com.safesmart.safesmart.controller.SafeMartLockController;

@SpringBootApplication
@Import(AppConfig.class)
public class SafesmartApplication {
	
	@Autowired
	DemoControllerInterface controllerInterface;

	public static void main(String[] args) {
		SpringApplication.run(SafesmartApplication.class, args);
	}
	
	@Bean
	public LockController getLockController() {
		LockController l = LockController.instance(new DefaultCredentials());
		
		l.start(controllerInterface);
		return l;
	}

}
