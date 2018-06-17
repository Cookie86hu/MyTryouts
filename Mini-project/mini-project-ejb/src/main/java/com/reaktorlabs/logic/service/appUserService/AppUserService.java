package com.reaktorlabs.logic.service.appUserService;

import com.reaktorlabs.logic.repository.ejbException.EJBException;
import com.reaktorlabs.logic.repository.userRepo.UserRepositoryInterface;
import com.reaktorlabs.model.user.AppUser;
import com.reaktorlabs.model.user.UserServiceInterface;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AppUserService implements UserServiceInterface {
    
    UserRepositoryInterface repo;

    public AppUserService() {
    }

    @Inject
    public AppUserService(UserRepositoryInterface repo) {
        this.repo = repo;
    }
    
    @Override
    public AppUser add(AppUser user) {
        try {
            repo.create(user); 
            return user;
        } catch (EJBException ex) {
            Logger.getLogger(AppUserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public AppUser read(Long id) {
        try {
            return repo.getById(id);
        } catch (EJBException ex) {
            Logger.getLogger(AppUserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public AppUser update(AppUser user) {
       return repo.update(user);
    }

    @Override
    public void remove(Long id) {
       repo.remove(id);
    }

    @Override
    public AppUser readByEmail(String email) {
        try {
            return repo.getByEmail(email);
        } catch (EJBException ex) {
            Logger.getLogger(AppUserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<AppUser> getAll() {
      return repo.getAll();
    }


}