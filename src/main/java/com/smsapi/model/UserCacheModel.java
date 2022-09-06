package com.smsapi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class UserCacheModel {

	private Map<String,UserModel> usercache=new HashMap<String,UserModel>();
	
	private String memoryid;
	
}
