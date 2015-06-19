package com.petro.span.client;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;


public class EmailValidator implements ConstraintValidator<ValidEmail,String> {  
	  

	
	 private static String EMAIL_PATTERN = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";  
	 RegExp regExp;
	 MatchResult matcher;
	   


	 @Override  
	 public void initialize(ValidEmail email) {  
	   regExp = RegExp.compile(EMAIL_PATTERN,"i");
	   
	 }  
	  
	 @Override  
	 public boolean isValid(String email, ConstraintValidatorContext context) {  
		 System.out.println("isValid");
	  boolean isValidEmail = false;  
	    
	  String emailstr = email;   
	   matcher = regExp.exec(emailstr);
	   
	   boolean matchFound = matcher != null;
	  if(matchFound){  
	   isValidEmail = true;  
	  }  
	   

	  return isValidEmail;  
	    
	 }  
	}
