package com.petro.span.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CurrentUser implements IsSerializable{

	CurrentUser(){
		System.out.println("CurrentUser constructor");
	}
	
	
	private String username;
	
	private List<String> roles;
	
	private Boolean isLoggedIn = false;
	
	private PrivilegedTabModel tabModel;
	
	private PrivilegedFiltersModel filtersModel;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean IsLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(Boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public PrivilegedTabModel getTabModel() {
		return tabModel;
	}

	public void setTabModel(PrivilegedTabModel tabModel) {
		this.tabModel = tabModel;
	}

	public PrivilegedFiltersModel getFiltersModel() {
		return filtersModel;
	}

	public void setFiltersModel(PrivilegedFiltersModel filtersModel) {
		this.filtersModel = filtersModel;
	}

	

	
	
	
	
	
	
	
}
