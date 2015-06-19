package com.petro.span.client.application.login;

import com.gwtplatform.mvp.client.UiHandlers;
import com.petro.span.shared.UserDto;

interface AuthorizationPageUiHandlers extends UiHandlers {


	void login(UserDto userDto);

}