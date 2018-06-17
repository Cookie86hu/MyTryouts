package com.reaktorlabs.logic.repository.mealRepo;

import com.reaktorlabs.logic.repository.ejbException.EJBException;
import com.reaktorlabs.logic.repository.mealRepo.exception.MealNotFoundException;
import com.reaktorlabs.model.meal.Meal;
import com.reaktorlabs.model.user.AppUser;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class MealRepositoryImplementation implements MealRepositoryInterface {

    @PersistenceContext(name = "mini_project_db")
    private EntityManager em;

    @Override
    public Meal create(Meal element) {
        em.persist(element);
        return element;
    }

    @Override
    public Meal getById(Long id) throws EJBException {
       
            Meal meal = em.find(Meal.class, id);
            if (null != meal) {
                return meal;
            }
            throw new MealNotFoundException("no meal item found with this parameter");
     
    }

    @Override
    public Meal update(Meal element) {
        em.merge(element);
        return element;
    }

    @Override
    public void remove(Long id) {
        em.remove(em.find(Meal.class, id));
    }

    @Override
    public List<Meal> getAll() {
        TypedQuery<Meal> query = em.createNamedQuery("Meal.findAll", Meal.class);
        return Collections.unmodifiableList(query.getResultList());
    }

    @Override
    public List<Meal> getByUser(AppUser user) {
        TypedQuery<Meal> query = em.createNamedQuery("Meal.findByUser", Meal.class);
        query.setParameter("userMeal", user);
        return query.getResultList();
    }

    @Override
    public Meal getByUserAndDateTime(AppUser user, LocalDateTime date) {
        TypedQuery<Meal> query = em.createQuery
        ("SELECT m FROM Meal m WHERE m.userMeal = :user AND m.consDate = :date", Meal.class);
        query.setParameter("user", user);
        query.setParameter("date", date);
        return query.getResultList().get(0);
    }

}
