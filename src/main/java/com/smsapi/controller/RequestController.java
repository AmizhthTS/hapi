package com.smsapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.redisapi.model.QueueName;
import com.redisapi.model.RedisTemplatePool;
import com.smsapi.model.RequestModel;
import com.smsapi.model.UserCacheModel;
import com.smsapi.service.RedisService;
import com.smsapi.util.StatusCode;

@RestController
public class RequestController {
	
	@Autowired UserCacheModel usercache;
	
	@Autowired RedisTemplatePool redistemplatepool;
	
	@Autowired RedisService redisservice;
	
	@PostMapping("/send")
	public ResponseEntity<?> login(@Valid @RequestBody RequestModel requestModel) {
		
		if(redistemplatepool.isAvailable(QueueName.ROUTER)) {
			
			if(redisservice.addQueue(requestModel)) {
				
				requestModel.setStatuscode(StatusCode.ACCEPT);
			
			}

		}
	
		return ResponseEntity.ok().body(requestModel.getResponse());
	}
	
	
	@PostMapping("/getmemory")
	public ResponseEntity<?> getmemory() {
		
		return ResponseEntity.ok().body(usercache);
	}

	
	
	
}