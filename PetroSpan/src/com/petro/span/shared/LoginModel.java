package com.petro.span.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LoginModel implements IsSerializable{

	private	List<String> role_list ;
	private	List<String> returnMsgList ;
	private	PrivilegedTabModel model;
	private PrivilegedFiltersModel filtersModel;
	
	
	public List<String> getRole_list() {
		return role_list;
	}
	
	public void setRole_list(List<String> role_list) {
		this.role_list = role_list;
	}
	
	public List<String> getReturnMsgList() {
		return returnMsgList;
	}
	
	public void setReturnMsgList(List<String> returnMsgList) {
		this.returnMsgList = returnMsgList;
	}
	
	public PrivilegedTabModel getModel() {
		return model;
	}
	
	public void setModel(PrivilegedTabModel model) {
		this.model = model;
	}

	public PrivilegedFiltersModel getFiltersModel() {
		return filtersModel;
	}

	public void setFiltersModel(PrivilegedFiltersModel filtersModel) {
		this.filtersModel = filtersModel;
	}

	
	
}
