package com.petro.span.client;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.petro.span.shared.RegistrationModel;


public class ClientValidation extends Validation {
    private Validator validator;
    private Set<ConstraintViolation<RegistrationModel>> constraintViolations;
 
    public ClientValidation() {
        this.validator = buildDefaultValidatorFactory().getValidator();
        System.out.println("ClientValidation constructor run");
    }
    public Set<ConstraintViolation<RegistrationModel>> getConstraintViolations(RegistrationModel object) {
        this.constraintViolations = validator.validate(object);
        return constraintViolations;
    }
    public String getPrimaryMessage() {
        return constraintViolations.iterator().next().getMessage();
    }
}