package com.smsapi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.smsapi.model.UserCacheModel;  
  
public class BalanceValidator implements ConstraintValidator<Balance,String> {  
  
	@Autowired
	UserCacheModel usercachemodel;
	
    public boolean isValid(String username, ConstraintValidatorContext cvc) {  
          
    
    	if(usercachemodel.getUsercache().containsKey(username)) {

    	if(usercachemodel.getUsercache().get(username).getBalanceavailable()==1) {
			
    		
			return true;
		}
    	
    	throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED,"username have no balance");

    	}
		
	
    	return true;
	
    }  
    
}  