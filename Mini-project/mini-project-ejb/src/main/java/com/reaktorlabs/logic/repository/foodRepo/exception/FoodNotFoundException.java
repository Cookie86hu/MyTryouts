package com.reaktorlabs.logic.repository.foodRepo.exception;

import com.reaktorlabs.logic.repository.ejbException.EJBException;

public class FoodNotFoundException extends EJBException {
    
    public FoodNotFoundException(String message){
        super(message);
    }
    
}
