package com.reaktorlabs.logic.repository.userRepo.exception;

import com.reaktorlabs.logic.repository.ejbException.EJBException;


public class AppUserAlreadyExsistsException extends EJBException {
     
    public AppUserAlreadyExsistsException(String message){
        super(message);
    }
}
