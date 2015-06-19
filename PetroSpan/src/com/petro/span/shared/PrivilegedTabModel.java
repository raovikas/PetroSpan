package com.petro.span.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PrivilegedTabModel implements IsSerializable{

	private String technical = "false";
	
	private String commercial = "false";
	
	private String forecast = "false";
	 
	private String production= "false";
	
	
	public String getTechnical() {
		return technical;
	}
	
	public void setTechnical(String technical) {
		this.technical = technical;
	}
	
	public String getCommercial() {
		return commercial;
	}
	
	public void setCommercial(String commercial) {
		this.commercial = commercial;
	}
	
	public String getForecast() {
		return forecast;
	}
	
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	public String getProduction() {
		return production;
	}
	
	public void setProduction(String production) {
		this.production = production;
	}
	
	
}
