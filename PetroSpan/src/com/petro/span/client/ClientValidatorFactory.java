package com.petro.span.client;

import javax.validation.Validator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;
import com.petro.span.shared.RegistrationModel;

public final class ClientValidatorFactory extends AbstractGwtValidatorFactory {
 
    // Syntax for multiple class:  @GwtValidation({action1.class, action2.class})
    @GwtValidation(RegistrationModel.class)
    public interface GwtValidator extends Validator {
    }
    
    
    @Override
    public AbstractGwtValidator createValidator() {
        return GWT.create(GwtValidator.class);
    }
}