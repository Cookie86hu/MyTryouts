package com.reaktorlabs.logic.repository.foodRepo;

import com.reaktorlabs.logic.repository.CRUD.GenericCRUDRepo;
import com.reaktorlabs.logic.repository.ejbException.EJBException;
import com.reaktorlabs.model.food.Food;
import java.util.List;


public interface FoodRepositoryInterface extends GenericCRUDRepo<Food, Long> {
    
    Food getByName(String name) throws EJBException;
    
    List<Food> getByNameList(String name);
    
    void forTest();
    
}
