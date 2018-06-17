package com.reaktorlabs.logic.util.authentication;

import com.reaktorlabs.model.user.AppUser;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class AuthService implements Authentication {

    private static final Logger LOGGER = Logger.getLogger(AuthService.class.getName());

    
    private HttpServletRequest servletRequest;

    public AuthService() {
    }
    
    @Inject
    public AuthService(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }
    
    

    @Override
    public void login(AppUser user) {
        try {
            servletRequest.getSession();
            servletRequest.login(user.getEmail(), user.getPassword());
        } catch (ServletException ex) {
            LOGGER.warning(ex.getMessage());
        }
    }


@Override
        public void logout() {
        try {
            servletRequest.getSession();
            servletRequest.logout();
        } catch (ServletException ex) {
            LOGGER.warning(ex.getMessage());
        }

    }

}
