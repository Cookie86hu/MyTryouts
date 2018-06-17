package com.reaktorlabs.logic.service.foodService;

import com.reaktorlabs.logic.repository.ejbException.EJBException;
import com.reaktorlabs.logic.repository.foodRepo.FoodRepositoryInterface;
import com.reaktorlabs.model.food.Food;
import com.reaktorlabs.model.food.FoodServiceInterface;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class FoodService implements FoodServiceInterface {

    private FoodRepositoryInterface repo;

    public FoodService() {
    }

    @Inject
    public FoodService(FoodRepositoryInterface repo) {
        this.repo = repo;
    }

    @Override
    public Food add(Food item) {
        try {
            return repo.create(item);
        } catch (EJBException ex) {
            Logger.getLogger(FoodService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;     
    }

    @Override
    public Food read(Long id) {
        try {
            return repo.getById(id);
        } catch (EJBException ex) {
            Logger.getLogger(FoodService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Food update(Food item) {
        return repo.update(item);
    }

    @Override
    public void remove(Long id) {
        repo.remove(id);
    }

    @Override
    public Food readByName(String name) {
        try {
            return repo.getByName(name);
        } catch (EJBException ex) {
            Logger.getLogger(FoodService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Food> getAll() {
        return repo.getAll();
    }

    @Override
    public Map<String, Double> getNutriantValues(Food item) {
        Map<String,Double> nutrianValues = new HashMap<>();
        nutrianValues.put("kCal", item.getCalories());
        nutrianValues.put("carb", item.getCarb());
        nutrianValues.put("prot", item.getProt());
        nutrianValues.put("fat", item.getFat());
        
        return nutrianValues;
     }

    @Override
    public List<Food> getByNameList(String name) {
        return repo.getByNameList(name);
    }

    @Override
    public void fillDB() {
        repo.forTest();
    }

}
