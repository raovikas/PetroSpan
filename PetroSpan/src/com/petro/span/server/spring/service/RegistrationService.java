package com.petro.span.server.spring.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.json.JSONException;

import com.petro.span.shared.AdminMasterModel;
import com.petro.span.shared.RegisteredUser;
import com.petro.span.shared.RegistrationModel;

public interface RegistrationService {

	String saveData(RegistrationModel model) throws IOException, GeneralSecurityException, JSONException;

	Boolean isEmailAlreadyExist(String emailAddress, String token);

	Boolean isLoginNameExist(String loginName, String token);

	List<RegisteredUser> getRegisteredUserDetails(String token);

//	String freezeUsersAccessRights(List<RegisteredUser> checkList, String token);

	String freezeUsersAccessRights(AdminMasterModel masterModel,
			String token);

}
