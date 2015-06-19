package com.petro.span.server.spring.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

import org.json.JSONException;

import com.petro.span.shared.LoginModel;
import com.petro.span.shared.UserDto;

public interface LoginService {

	Map<String, String> getUserInfo(String token) throws JSONException;
	
	Boolean isTokenExpire(String token) throws JSONException;
	
//	Boolean login(String username,String password);

//	 Map<String, List<String>> login(UserDto userDto) throws IOException, GeneralSecurityException, JSONException;
	
	 LoginModel login(UserDto userDto) throws IOException, GeneralSecurityException, JSONException;

}
