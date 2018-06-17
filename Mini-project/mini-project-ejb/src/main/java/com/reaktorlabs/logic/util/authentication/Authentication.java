
package com.reaktorlabs.logic.util.authentication;


import com.reaktorlabs.model.user.AppUser;


public interface Authentication  {
    
    void login(AppUser user);
    void logout();
    
    
}
