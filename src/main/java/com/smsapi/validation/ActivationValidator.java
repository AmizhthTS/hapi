package com.smsapi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.smsapi.model.UserCacheModel;  
  
public class ActivationValidator implements ConstraintValidator<Activation,String> {  
  
	@Autowired
	UserCacheModel usercachemodel;
	
    @SuppressWarnings("deprecation")
	public boolean isValid(String username, ConstraintValidatorContext cvc) {  
          
    	
    	if(usercachemodel.getUsercache().containsKey(username)) {
    	if(usercachemodel.getUsercache().get(username).getStatus()==1) {

    		return true;
		
    	}

    	throw new ResponseStatusException(HttpStatus.MOVED_TEMPORARILY,"username Disabled");

    	}else {
    		
    		return true;
    	}
    	
		

    }  
    
}  