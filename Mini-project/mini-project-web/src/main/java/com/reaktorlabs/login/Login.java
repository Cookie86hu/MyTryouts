/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.login;

import com.reaktorlabs.logic.repository.ejbException.EJBException;
import com.reaktorlabs.logic.util.authentication.Authentication;
import com.reaktorlabs.model.user.AppUser;
import com.reaktorlabs.model.user.UserServiceInterface;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named(value = "loginBean")
@RequestScoped
public class Login implements Serializable {

    private AppUser user;

    private Authentication authService;
    private UserServiceInterface userService;
    private HttpServletRequest req;

    @Inject
    public Login(Authentication authService, UserServiceInterface userService, HttpServletRequest req) {
        this.userService = userService;
        this.authService = authService;
        this.req = req;
        this.user = new AppUser();

    }
//Void???
    public String login() throws EJBException {
        authService.login(user);
        return "";
    }

    public Boolean renderLogOut() {
        if (req.getUserPrincipal() == null) {
            return false;
        }
        return true;
    }
    
     public Boolean renderLogin() {
        if (req.getUserPrincipal() == null) {
            return true;
        }
        return false;
    }
    
    public String logout(){
        authService.logout();
        return "/StartingPage.xhtml";
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Authentication getAuthService() {
        return authService;
    }

    public void setAuthService(Authentication authService) {
        this.authService = authService;
    }

}
