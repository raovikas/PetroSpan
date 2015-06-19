package com.petro.span.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TabSelectionModel implements IsSerializable{

	/**
	 * 
	 */
	private Boolean tabCheck;
	private String tabName;


	public Boolean isTabCheck() {
		return tabCheck;
	}
	public void setTabCheck(Boolean tabCheck) {
		this.tabCheck = tabCheck;
	}
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}



}
