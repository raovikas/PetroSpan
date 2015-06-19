package com.petro.span.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petro.span.server.spring.service.RegistrationService;
import com.petro.span.shared.AdminMasterModel;
import com.petro.span.shared.RegisteredUser;
import com.petro.span.shared.RegistrationModel;


@Controller
@RequestMapping(value = "/rest/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationController {


	@Autowired
	RegistrationService registrationService;


	@RequestMapping(method = RequestMethod.POST )
	@ResponseBody
	String saveData(@RequestBody  RegistrationModel model) throws IOException, GeneralSecurityException, JSONException{
		return registrationService.saveData(model);

	}


	@RequestMapping(method = RequestMethod.POST  , value = "/isEmailExist/{loginName}")
	@ResponseBody
	Boolean isLoginNameExist( @RequestBody String token , @PathVariable  String loginName){
		return registrationService.isLoginNameExist(loginName,token);

	}


	@RequestMapping(method = RequestMethod.POST  , value = "/isEmailExist")
	@ResponseBody
	Boolean isEmailAlreadyExist( @RequestParam(required = false) String token , @RequestParam(required = false)  String email){
		System.out.println("token "+token +"  email "+email);
		return registrationService.isEmailAlreadyExist(email,token);

	}


	@RequestMapping(method = RequestMethod.POST , value ="/userDetails")
	@ResponseBody
	List<RegisteredUser> getRegisteredUserDetails(@RequestBody  String token){
		return registrationService.getRegisteredUserDetails(token);

	}
	
	
//	@RequestMapping(method = RequestMethod.POST , value ="/freezeUser/{token}")
//	@ResponseBody
//	String freezeUsersAccessRights(@RequestBody List<RegisteredUser> checkList,  @PathVariable  String token){
//		return registrationService.freezeUsersAccessRights(checkList,token);
//		
//	}
	
	@RequestMapping(method = RequestMethod.POST , value ="/freezeUser/{token}")
	@ResponseBody
	String freezeUsersAccessRights(@RequestBody AdminMasterModel masterModel ,  @PathVariable  String token){
		return registrationService.freezeUsersAccessRights(masterModel,token);
		
	}

}
