package com.smsapi.service;

import static io.restassured.RestAssured.given;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.smsapi.model.TokenModel;
import com.smsapi.model.UserCacheModel;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Service
public class UserService {

	@Autowired TokenModel token;
	
	
	public String getUsersmemoryid() {
		
        Response response = given()
				 			.auth().oauth2(token.getToken())
        					.contentType(ContentType.JSON)
                            .when()
                            .post("/sms-api/user/getusersmemoryid");
                            
        if(response.getStatusCode()==200) {
        	return response.then().extract().asString();
        }
        
        return null;
	}
	public UserCacheModel getUsers() {
		
    	
    	Gson gson =new Gson();
        Response response = given()
        					.auth().oauth2(token.getToken())
        					.contentType(ContentType.JSON)
                            .when()
                            .post("/sms-api/user/getusers");

        
                            
        if(response.getStatusCode()==200) {
        	return gson.fromJson(response.then().extract().asString(), UserCacheModel.class);
        }
        
        return null;
	}
}