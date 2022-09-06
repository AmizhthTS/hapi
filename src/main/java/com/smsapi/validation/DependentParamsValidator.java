package com.smsapi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.smsapi.model.RequestModel;
import com.smsapi.model.UserCacheModel;  
  
public class DependentParamsValidator implements ConstraintValidator<DependentParams,RequestModel> {  
  
	@Autowired
	UserCacheModel usercachemodel;
	
    public boolean isValid(RequestModel object, ConstraintValidatorContext cvc) {  
        
    		boolean isEntity=true;
    		
    		boolean isTemplateId=true;

    	 if (object instanceof RequestModel) {
    		 
    		if (object.getEntityid()==null||object.getEntityid().trim().length()<1) {
    			
    			isEntity=false;
    		}
    		
    		if(object.getTemplateid()==null||object.getTemplateid().trim().length()<1) {
    			
    			isTemplateId=false;
    		}
    		
    		if((isEntity==false&&isTemplateId==false)) {
    			
    			return true;
    		}else if(isEntity&&isTemplateId) {
    			
    			return true;
    		}else {
    			
    			return false;
    		}
    		 
         }else {
        	 
             throw new IllegalArgumentException("@DependentParams only applies to RequestModel objects");

         }
    	
    }  
    
    
}  