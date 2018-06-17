package com.reaktorlabs.logic.repository.mealRepo.exception;

import com.reaktorlabs.logic.repository.ejbException.EJBException;


public class MealNotFoundException extends EJBException {
    
    public MealNotFoundException(String message){
        super(message);
    }
    
}
