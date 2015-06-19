package com.petro.span.client.rest;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.petro.span.shared.LoginModel;
import com.petro.span.shared.UserDto;

@Path("/login")
public interface ILoginService {

	@Path("/userInfo")
	@POST
	RestAction<Map<String, String>> getUserInfo(String token);


	@Path("/isTokenExpire")
	@POST
	RestAction<Boolean> isTokenExpire(String token);





//	@POST
//	@Consumes(APPLICATION_FORM_URLENCODED)
//	RestAction<Boolean> login(@FormParam("username") String username, @FormParam("password") String password);
	
//	@POST
//	RestAction< Map<String, List<String>>> login(UserDto userDto);
	
	@POST
	RestAction<LoginModel> login(UserDto userDto);


}
