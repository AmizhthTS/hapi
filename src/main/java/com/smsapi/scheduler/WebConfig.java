package com.smsapi.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import com.smsapi.model.TokenModel;
import com.smsapi.model.UserCacheModel;
import com.smsapi.service.TokenService;
import com.smsapi.service.UserService;

@Service
@Configuration
@EnableScheduling
public class WebConfig {
	
	@Autowired TokenService tokenservice;
	
	@Autowired UserService userservice;
	
	@Bean("usercache")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	@DependsOn("tokenmodel")
	public UserCacheModel usercacheModel() {

		UserCacheModel ucm=userservice.getUsers();
		
		return UserCacheModel.builder().memoryid(ucm.getMemoryid()).usercache(ucm.getUsercache()).build();
	
	}
	

	
	@Bean("tokenmodel")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public TokenModel setTokenModel() {
		
		
		return TokenModel.builder().token(tokenservice.getToken()).build();
	}
	
	/*
	
	@Bean("loginjob")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    @ConditionalOnProperty(value = "jobs.enabled", matchIfMissing = true, havingValue = "true")
	@DependsOn({"tokenmodel","usercache"})
	public LoginJob getLoginJob() {
		
		return new LoginJob();
	}
	
	
	@Bean("scheduleuserjob")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    @ConditionalOnProperty(value = "jobs.enabled", matchIfMissing = true, havingValue = "true")
	@DependsOn("loginjob")
	public GetUserJob getGetUserJob() {
		
		return new GetUserJob();
	}
	
	*/
}
