package com.reaktorlabs.logic.repository.mealRepo;

import com.reaktorlabs.logic.repository.CRUD.GenericCRUDRepo;
import com.reaktorlabs.model.meal.Meal;
import com.reaktorlabs.model.user.AppUser;
import java.time.LocalDateTime;
import java.util.List;

public interface MealRepositoryInterface extends GenericCRUDRepo<Meal,Long> {
    
    List<Meal> getByUser(AppUser user);
    
    Meal getByUserAndDateTime(AppUser user, LocalDateTime date);
}
