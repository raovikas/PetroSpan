package com.petro.span.shared;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.petro.span.client.ValidEmail;
import com.petro.span.client.ValidPassword;

public class RegistrationModel implements IsSerializable{

	@Size(min = 3 , max = 25 , message = "Name must be between 3 to 25 digit")
	private String userName;
	
	@ValidEmail(message = "Email Address is not in a valid format")
	private String emailAddress;
	
	
	@Size(min = 3, max = 10 , message = "Login Name must be between 3 to 10 digit")
	private String loginName;
	
	
	@ValidPassword(message = "Password must be at least 8 characters long")
	private String hashedPassword;//password
	
	
	@NotNull(message = "Please select the gender")
	private String gender;
	

   private String confirmPassword;

	private String accessToken ;




	public RegistrationModel(){

	}

	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getUserName() {
		return userName;
	}
	
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getLoginName() {
		return loginName;
	}
	
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getHashedPassword() {
		return hashedPassword;
	}


	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}



}
