package com.petro.span.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PrivilegedFiltersModel implements IsSerializable{

	private String quailtyZone = "false";
	private String townshipRange = "false";
	private String operator = "false";
	private String leaseName = "false";
	private String wellName = "false";
	private String wellType = "false";
	private String wellOrientation = "false";
	private String fluidType = "false";
	private String wellStatus = "false";
	private String producingZone = "false";
	private String permitDate = "false";
	private String spudDate = "false";
	private String completionDate = "false";
	private String firstProductionDt = "false";
	private String searchBy = "false";
	
	
	public String getQuailtyZone() {
		return quailtyZone;
	}
	public void setQuailtyZone(String quailtyZone) {
		this.quailtyZone = quailtyZone;
	}
	public String getTownshipRange() {
		return townshipRange;
	}
	public void setTownshipRange(String townshipRange) {
		this.townshipRange = townshipRange;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getLeaseName() {
		return leaseName;
	}
	public void setLeaseName(String leaseName) {
		this.leaseName = leaseName;
	}
	public String getWellName() {
		return wellName;
	}
	public void setWellName(String wellName) {
		this.wellName = wellName;
	}
	public String getWellType() {
		return wellType;
	}
	public void setWellType(String wellType) {
		this.wellType = wellType;
	}
	public String getWellOrientation() {
		return wellOrientation;
	}
	public void setWellOrientation(String wellOrientation) {
		this.wellOrientation = wellOrientation;
	}
	public String getFluidType() {
		return fluidType;
	}
	public void setFluidType(String fluidType) {
		this.fluidType = fluidType;
	}
	public String getWellStatus() {
		return wellStatus;
	}
	public void setWellStatus(String wellStatus) {
		this.wellStatus = wellStatus;
	}
	public String getProducingZone() {
		return producingZone;
	}
	public void setProducingZone(String producingZone) {
		this.producingZone = producingZone;
	}
	public String getPermitDate() {
		return permitDate;
	}
	public void setPermitDate(String permitDate) {
		this.permitDate = permitDate;
	}
	public String getSpudDate() {
		return spudDate;
	}
	public void setSpudDate(String spudDate) {
		this.spudDate = spudDate;
	}
	public String getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}
	public String getFirstProductionDt() {
		return firstProductionDt;
	}
	public void setFirstProductionDt(String firstProductionDt) {
		this.firstProductionDt = firstProductionDt;
	}
	public String getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	
	
	
	
}
