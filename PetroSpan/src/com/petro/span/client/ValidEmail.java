package com.petro.span.client;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType; 
import java.lang.annotation.RetentionPolicy;

import com.petro.span.client.EmailValidator;

import javax.validation.Constraint;

@Documented  
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.ANNOTATION_TYPE})  
@Constraint(validatedBy=EmailValidator.class)  
public @interface ValidEmail {  
   
 String message();  
  
    @SuppressWarnings("rawtypes")
	Class[] groups() default {};  
      
    @SuppressWarnings("rawtypes")
	Class[] payload() default {};  
}  
