package com.reaktorlabs.model.food;

import com.reaktorlabs.model.CRUDService.CRUDService;
import java.util.List;
import java.util.Map;

public interface FoodServiceInterface extends CRUDService<Food, Long> {
 
    Food readByName(String name);
    
    Map<String,Double> getNutriantValues(Food item);
    
    List<Food> getByNameList(String name);
    
    void fillDB();
    
    
}
