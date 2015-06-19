package com.petro.span.client.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.gwtplatform.dispatch.rest.shared.RestAction;
import com.petro.span.shared.AdminMasterModel;
import com.petro.span.shared.RegisteredUser;
import com.petro.span.shared.RegistrationModel;

@Path("/register")
public interface RegistrationRest {

	@POST
	RestAction<String>  saveData(RegistrationModel model);

	@Path("/isEmailExist/{loginName}")
	@POST
	RestAction<Boolean> isLoginNameExist(String token , @PathParam("loginName")  String loginName);


	@Path("/isEmailExist")
	@POST
	@Consumes(APPLICATION_FORM_URLENCODED)
	RestAction<Boolean> isEmailAlreadyExist(@FormParam("token")  String token , @FormParam("email")  String email);

	@Path("/userDetails")
	@POST
	RestAction<List<RegisteredUser>> getRegisteredUserDetails(String token);
	
//	@Path("/freezeUser/{token}")
//	@POST
//	RestAction<String> freezeUsersAccessRights(List<RegisteredUser> checkList, @PathParam("token") String token);
	
	@Path("/freezeUser/{token}")
	@POST
	<T> RestAction<String> freezeUsersAccessRights(AdminMasterModel masterModel, @PathParam("token") String token);

}
