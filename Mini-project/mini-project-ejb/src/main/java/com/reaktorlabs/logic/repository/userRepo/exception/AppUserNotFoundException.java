package com.reaktorlabs.logic.repository.userRepo.exception;

import com.reaktorlabs.logic.repository.ejbException.EJBException;

public class AppUserNotFoundException extends EJBException {
    
    public AppUserNotFoundException(String message){
        super(message);
    }
    
}
