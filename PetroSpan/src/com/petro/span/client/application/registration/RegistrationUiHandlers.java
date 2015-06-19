package com.petro.span.client.application.registration;

import com.gwtplatform.mvp.client.UiHandlers;
import com.petro.span.shared.RegistrationModel;
import com.petro.span.shared.UserInfo;

interface RegistrationUiHandlers extends UiHandlers {

	void registerUserProcessing(RegistrationModel model, UserInfo user);


	void isEmailAlreadyExist(String token ,String email );


	void isLoginNameExist(String token, String value);
}