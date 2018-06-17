package com.reaktorlabs.model.meal;

import com.reaktorlabs.model.CRUDService.CRUDService;
import com.reaktorlabs.model.user.AppUser;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface MealServiceInterface extends CRUDService<Meal, Long> {
    
    List<Meal> readByUser(AppUser user);
    
    List<Meal> findMealsForUserByDate(AppUser user, LocalDate date);
    
    Map<String,Double> dailyConsumtionOfNutrients(AppUser user, LocalDate date);
    
    Meal readByUserAndDateTime(AppUser user, LocalDateTime date);
    
    List<String> datesOfMealsForUserForChart(AppUser user);
    
    Map<String,Double> allTimeNutrientConsumption(AppUser user);
    
         
}
