package com.reaktorlabs.logic.repository.foodRepo;

import com.reaktorlabs.logic.repository.ejbException.EJBException;
import com.reaktorlabs.logic.repository.foodRepo.exception.FoodAlreadyExistsException;
import com.reaktorlabs.logic.repository.foodRepo.exception.FoodNotFoundException;
import com.reaktorlabs.model.food.Food;
import com.reaktorlabs.model.user.ALLERGIES;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class FoodRepositoryImplementation implements FoodRepositoryInterface {

    @PersistenceContext(name = "mini_project_db")
    private EntityManager em;

    @Override
    public Food create(Food element) throws EJBException {
        //try {
        if (checkDB(element.getName()) == null) {
            em.persist(element);
            return element;
        }
        throw new FoodAlreadyExistsException("food is already registered");
        /*} catch (FoodAlreadyExistsException ex) {
            Logger.getLogger(UserRepositoryImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;*/
    }

    @Override
    public Food getById(Long id) throws EJBException {
       
        Food item = em.find(Food.class, id);
        if (null != item) {
            return item;
        }
        throw new FoodNotFoundException("no food item found with this parameter");
      
    }

    @Override
    public Food update(Food element) {
        em.merge(element);
        return element;
    }

    @Override
    public void remove(Long id) {
        em.remove(em.find(Food.class, id));
    }

    @Override
    public List<Food> getAll() {
        TypedQuery<Food> query = em.createNamedQuery("Food.findAll", Food.class);
        return query.getResultList();
    }

    @Override
    public Food getByName(String name) throws EJBException {
       
        TypedQuery<Food> query = em.createNamedQuery("Food.findByName", Food.class);
        query.setParameter("name", name + "%");
        if (0 < query.getResultList().size()) {
            return query.getResultList().get(0);
        }
        throw new FoodNotFoundException("no food item found with this parameter");
       
    }

/*FOR TESTING STRICKLY!!!*/
    private void forTesting() {
        String word = "dummy";
        Set<ALLERGIES> allergyList1 = new HashSet<>();
        allergyList1.add(ALLERGIES.MILK);
        allergyList1.add(ALLERGIES.GLUTEIN);
        Set<ALLERGIES> allergyList2 = new HashSet<>();
        allergyList2.add(ALLERGIES.SOY);
        if (getAll().isEmpty()) {

            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0) {
                    Food food = new Food();
                    food.setName(word + i);
                    food.setCalories(1.0);
                    food.setCarb(2.0);
                    food.setFat(3.0);
                    food.setProt(4.0);
                    food.setAllergens(allergyList1);
                    em.persist(food);
                } else {
                    Food food = new Food();
                    food.setName(word + i);
                    food.setCalories(1.0);
                    food.setCarb(2.0);
                    food.setFat(3.0);
                    food.setProt(4.0);
                    food.setAllergens(allergyList2);
                    em.persist(food);
                }
            }
        }
    }

    private Food checkDB(String name) {
        TypedQuery<Food> query = em.createQuery("SELECT f FROM Food f WHERE f.name = :name", Food.class);
        query.setParameter("name", name);
        if (0 < query.getResultList().size()) {
            return query.getResultList().get(0);
        }
        return null;
    }

    @Override
    public List<Food> getByNameList(String name) {
        List<Food> list = new ArrayList<>();
        TypedQuery<Food> query = em.createQuery("SELECT f FROM Food f WHERE f.name LIKE :name", Food.class);
        query.setParameter("name", name + "%");
        if (0 < query.getResultList().size()) {
            list = query.getResultList();

        }
        return list;
    }

    @Override
    public void forTest() {
        forTesting();
    }

}
