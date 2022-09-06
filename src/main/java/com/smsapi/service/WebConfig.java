package com.smsapi.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.smsapi.model.TokenModel;
import com.smsapi.model.UserCacheModel;


@Service
public class WebConfig {
	
	@Bean("usercache")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public UserCacheModel usercacheModel() {

		return new UserCacheModel();
	
	}
	

	
	@Bean("tokenmodel")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public TokenModel setTokenModel() {
		
		return new TokenModel();
	}
}
