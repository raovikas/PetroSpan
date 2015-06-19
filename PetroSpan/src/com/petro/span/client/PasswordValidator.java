package com.petro.span.client;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

public class PasswordValidator implements ConstraintValidator<ValidPassword,String> {

	 private static String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";  
	 RegExp regExp;
	 MatchResult matcher;
	   


	 @Override  
	 public void initialize(ValidPassword password) {  
	   regExp = RegExp.compile(PASSWORD_PATTERN);
	   
	 }  
	  
	 @Override  
	 public boolean isValid(String password, ConstraintValidatorContext context) {  
	  boolean isValidPassword = false;  
	    
	   matcher = regExp.exec(password);
	   
	   boolean matchFound = matcher != null;
	  if(matchFound){  
	   isValidPassword = true;  
	  }  
	   

	  return isValidPassword;  
	    
	 }  
}
