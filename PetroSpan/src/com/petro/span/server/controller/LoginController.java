package com.petro.span.server.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petro.span.server.spring.service.LoginService;
import com.petro.span.shared.LoginModel;
import com.petro.span.shared.UserDto;




@Controller
@RequestMapping("/rest/login")
public class LoginController {

	@Autowired
	LoginService gloginService;


	@RequestMapping(method = POST,value ="/userInfo")
	@ResponseBody
	Map<String, String>  getUserInfo(@RequestBody String token) throws Exception{
		return gloginService.getUserInfo(token);

	}


	@RequestMapping(method = POST,value ="/isTokenExpire")
	@ResponseBody
	Boolean  isTokenExpire(@RequestBody String token) throws Exception{
		return gloginService.isTokenExpire(token);

	}


	//	@RequestMapping(method = POST)
	//    @ResponseBody
	//    boolean login(@RequestParam String username, @RequestParam String password){
	//    	System.out.println("loginController");
	//    	return gloginService.login(username, password);
	//    }

	//	@RequestMapping(method = POST)
	//	@ResponseBody
	//	 Map<String, List<String>> login(@RequestBody UserDto userDto) throws IOException, GeneralSecurityException, JSONException{
	//		return gloginService.login(userDto);
	//		
	//	}


	@RequestMapping(method = POST)
	@ResponseBody
	LoginModel login(@RequestBody UserDto userDto) throws IOException, GeneralSecurityException, JSONException{
		return gloginService.login(userDto);

	}
}
