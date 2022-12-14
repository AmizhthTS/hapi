package com.smsapi.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.smsapi.model.UserCacheModel;
import com.smsapi.service.UserService;

@Service
public class GetUserJob {

	@Autowired UserService userservice;
	
	@Autowired UserCacheModel usercache;
	
	
	

	@Scheduled(fixedDelay=1000)
	public void getUsersCache() {
		
		String memoryid=userservice.getUsersmemoryid();
		
		System.out.print("memoryid : "+memoryid);
		
		if(memoryid!=null) {
		
			if(usercache.getMemoryid()==null||!memoryid.equals(usercache.getMemoryid())) {
				
			UserCacheModel ucm=userservice.getUsers();
			
			if(ucm!=null) {
				
				if(ucm.getMemoryid()!=null&&ucm.getUsercache()!=null) {
				
					usercache.setUsercache(ucm.getUsercache());

					usercache.setMemoryid(ucm.getMemoryid());
					
				}
			}
			}
		}
	
	}
	
}
