package com.reaktorlabs.logic.repository.userRepo;

import com.reaktorlabs.logic.repository.ejbException.EJBException;
import com.reaktorlabs.logic.repository.userRepo.exception.AppUserAlreadyExsistsException;
import com.reaktorlabs.logic.repository.userRepo.exception.AppUserNotFoundException;
import com.reaktorlabs.logic.util.authentication.HashUtils;
import com.reaktorlabs.model.user.AppUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserRepositoryImplementation implements UserRepositoryInterface {

    @PersistenceContext(name = "mini_project_db")
    private EntityManager em;

    @Override
    public AppUser getByEmail(String email) throws EJBException {
       
            TypedQuery<AppUser> query = em.createNamedQuery("AppUser.findByEmail", AppUser.class);
            query.setParameter("email", email);
            if (0 < query.getResultList().size()) {
                return query.getResultList().get(0);
            }
            throw new AppUserNotFoundException("no user found with this parameter");
      
            
    }

    @Override
    public AppUser create(AppUser element) throws EJBException {
        
            if (checkDB(element.getEmail()) == null) {
                String pass = HashUtils.encryptSHA512(element.getPassword());
                element.setPassword(pass);
                element.setAppUserRole("user");
                em.persist(element);
                return element;
            }
            throw new AppUserAlreadyExsistsException("user is already registered");
      
    }

    @Override
    public AppUser getById(Long id) throws EJBException {
       
            AppUser user = em.find(AppUser.class, id);
            if (null != user) {
                return user;
            }
            throw new AppUserNotFoundException("no user found with this parameter");
      
    }

    @Override
    public AppUser update(AppUser element) {
        em.merge(element);
        return element;

    }

    @Override
    public void remove(Long id) {
        em.remove(id);
    }

    @Override
    public List<AppUser> getAll() {
        TypedQuery<AppUser> query = em.createNamedQuery("AppUser.findAll", AppUser.class);
        return query.getResultList();              
    }
    
    private AppUser checkDB(String email){
         TypedQuery<AppUser> query = em.createQuery("SELECT u FROM AppUser u WHERE u.email = :email", AppUser.class);
            query.setParameter("email", email);
            if (0 < query.getResultList().size()) {
                return query.getResultList().get(0);
            }
            return null;
    }

}
