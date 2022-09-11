package com.smsapi.service;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.stereotype.Service;

import com.redisapi.model.QueueName;
import com.redisapi.model.RedisTemplatePool;
import com.smsapi.model.RequestModel;

@Service
public class RedisService {

	@Autowired RedisTemplatePool redistemplatepool;

	public boolean addQueue(RequestModel requestmodel) {
		

		RedisConnection connection=null;
		ObjectOutput oo = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
		
		connection=redistemplatepool.getTemplate(QueueName.ROUTER).getConnectionFactory().getConnection();
		
		oo = new ObjectOutputStream(bos);
		oo.writeObject(requestmodel);
		long cnt=connection.lPush(QueueName.ROUTER.getBytes(), bos.toByteArray());		
		
		if(cnt>0) {
			
			return true;
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return false;
		
	}
	
}
