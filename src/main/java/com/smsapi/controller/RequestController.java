package com.smsapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smsapi.model.RequestModel;
import com.smsapi.model.UserCacheModel;

@RestController
public class RequestController {
	
	@Autowired UserCacheModel usercache;
	
	@PostMapping("/send")
	public ResponseEntity<?> login(@Valid @RequestBody RequestModel requestModel) {
		
		return ResponseEntity.ok().body(requestModel.getResponse());
	}
	
	
	@PostMapping("/getmemory")
	public ResponseEntity<?> getmemory() {
		
		return ResponseEntity.ok().body(usercache);
	}

	
	
	
}