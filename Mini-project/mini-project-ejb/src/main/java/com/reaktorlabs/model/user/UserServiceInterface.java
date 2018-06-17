package com.reaktorlabs.model.user;

import com.reaktorlabs.model.CRUDService.CRUDService;


public interface UserServiceInterface extends CRUDService<AppUser, Long> {
    
    AppUser readByEmail(String email);
   
}
