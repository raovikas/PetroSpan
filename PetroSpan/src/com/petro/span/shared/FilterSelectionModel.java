package com.petro.span.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FilterSelectionModel implements IsSerializable{

	
  
private Boolean filterCheck;
  private String filterName;
  
public Boolean isFilterCheck() {
	return filterCheck;
}
public void setFilterCheck(Boolean filterCheck) {
	this.filterCheck = filterCheck;
}
public String getFilterName() {
	return filterName;
}
public void setFilterName(String filterName) {
	this.filterName = filterName;
}
  
  
}
