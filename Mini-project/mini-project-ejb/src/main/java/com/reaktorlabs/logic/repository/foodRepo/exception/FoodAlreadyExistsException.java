package com.reaktorlabs.logic.repository.foodRepo.exception;

import com.reaktorlabs.logic.repository.ejbException.EJBException;


public class FoodAlreadyExistsException extends EJBException {
     
    public FoodAlreadyExistsException(String message){
        super(message);
    }
}
