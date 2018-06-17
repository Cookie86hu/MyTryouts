package com.reaktorlabs.logic.repository.userRepo;

import com.reaktorlabs.logic.repository.CRUD.GenericCRUDRepo;
import com.reaktorlabs.logic.repository.ejbException.EJBException;
import com.reaktorlabs.model.user.AppUser;

public interface UserRepositoryInterface extends GenericCRUDRepo<AppUser,Long>{
    
    AppUser getByEmail(String email) throws EJBException;
    
}
