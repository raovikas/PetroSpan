package com.petro.span.shared;


import java.io.Serializable;

public class UserInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	private String emailId;
	private String token;
	private String userName;
	private String userId;
	private String state;
	private String userAgent;
	private String code;
	private String refreshToken;
	private long refreshTime;
	private double expireIn;
	private String idToken;
	private String clientId;
	private String clientSecret;
	private String redirectUri;
	private String pictureURL;

	
	private Boolean isTokenExpire = false;


	
	

	

	public Boolean getIsTokenExpire() {
		return isTokenExpire;
	}

	public void setIsTokenExpire(Boolean isTokenExpire) {
		this.isTokenExpire = isTokenExpire;
	}

	public UserInfo(){
		System.out.println("userInfo");
	}

	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setRefreshTime(long refreshTime) {
		this.refreshTime = refreshTime;
	}
	public long getRefreshTime() {
		return refreshTime;
	}
	//	public void setExpireIn(long expireIn) {
	//		this.expireIn = expireIn;
	//	}
	//	public long getExpireIn() {
	//		return expireIn;
	//	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}
	public String getIdToken() {
		return idToken;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	public double getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(double expireIn) {
		this.expireIn = expireIn;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}



}
