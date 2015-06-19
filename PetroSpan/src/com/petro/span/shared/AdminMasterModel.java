package com.petro.span.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AdminMasterModel implements IsSerializable{

	List<RegisteredUser> checkList;
	List<FilterSelectionModel> filtersCheckList;
	List<TabSelectionModel> tabsCheckList;
	
	
	public List<RegisteredUser> getCheckList() {
		return checkList;
	}
	
	public void setCheckList(List<RegisteredUser> checkList) {
		this.checkList = checkList;
	}
	
	public List<FilterSelectionModel> getFiltersCheckList() {
		return filtersCheckList;
	}
	
	public void setFiltersCheckList(List<FilterSelectionModel> filtersCheckList) {
		this.filtersCheckList = filtersCheckList;
	}
	
	public List<TabSelectionModel> getTabsCheckList() {
		return tabsCheckList;
	}
	
	public void setTabsCheckList(List<TabSelectionModel> tabsCheckList) {
		this.tabsCheckList = tabsCheckList;
	}
	
	
}
