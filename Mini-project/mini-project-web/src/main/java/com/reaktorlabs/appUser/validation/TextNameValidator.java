/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.appUser.validation;

import java.util.Map;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.validate.ClientValidator;

@FacesValidator("nameValidator")
public class TextNameValidator implements Validator, ClientValidator {
    
    private Pattern pattern;
  
    private static final String NAME_PATTERN = "^[^±!@£$%^&*_+§¡€#¢§¶•ªº«\\\\/<>?:;|=.,]{1,20}$";
  
    public TextNameValidator() {
        pattern = Pattern.compile(NAME_PATTERN);
    }
 
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value == null) {
            return;
        }
         
        if(!pattern.matcher(value.toString()).matches()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", 
                        value + " contains wrong characters!;"));
        }
    }
 
    @Override
    public Map<String, Object> getMetadata() {
        return null;
    }
 
    @Override
    public String getValidatorId() {
        return "nameValidator";
    }
     
}
   
    
    
    

