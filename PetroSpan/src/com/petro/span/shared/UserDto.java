package com.petro.span.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserDto implements IsSerializable{

	private String loginName;
	private String accessToken;
	private String password;
	
	public UserDto(){
		System.out.println("UserDto");
	}
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
