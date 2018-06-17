package com.reaktorlabs.logic.service.mealService;

import com.reaktorlabs.logic.repository.ejbException.EJBException;
import com.reaktorlabs.logic.repository.mealRepo.MealRepositoryInterface;
import com.reaktorlabs.model.food.FoodServiceInterface;
import com.reaktorlabs.model.meal.Meal;
import com.reaktorlabs.model.meal.MealServiceInterface;
import com.reaktorlabs.model.user.AppUser;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.inject.Inject;

@Stateful 
public class MealService implements MealServiceInterface {

    private MealRepositoryInterface repo;
    private FoodServiceInterface foodService;
    

    public MealService() {
    }

    @Inject
    public MealService(MealRepositoryInterface repo, FoodServiceInterface foodService) {
        this.repo = repo;
        this.foodService = foodService;
    }

    @Override
    public Meal add(Meal meal) {
        try {
            return repo.create(meal);
        } catch (EJBException ex) {
            Logger.getLogger(MealService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Meal read(Long id) {
        try {
            return repo.getById(id);
        } catch (EJBException ex) {
            Logger.getLogger(MealService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Meal update(Meal meal) {
        return repo.update(meal);
    }

    @Override
    public void remove(Long id) {
        repo.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return repo.getAll();
    }

    @Override
    public List<Meal> readByUser(AppUser user) {
        return repo.getByUser(user);
    }

    @Override
    public List<Meal> findMealsForUserByDate(AppUser user, LocalDate date) {
        List<Meal> result = new ArrayList<>();
        for (Meal item : readByUser(user)) {
            if (item.getConsDate().toLocalDate().equals(date)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public Map<String, Double> dailyConsumtionOfNutrients(AppUser user, LocalDate date) {
        List<Meal> mealList = findMealsForUserByDate(user, date);
        Double kCalTotal = 0.0;
        Double carbTotal = 0.0;
        Double protTotal = 0.0;
        Double fatTotal = 0.0;

        for (Meal meal : mealList) {
            kCalTotal += meal.getCalories();
            carbTotal += meal.getCarbs();
            protTotal += meal.getProtein();
            fatTotal += meal.getFat();
        }

        Map<String, Double> result = new HashMap<>();
        result.put("kCal", kCalTotal);
        result.put("carb", carbTotal);
        result.put("prot", protTotal);
        result.put("fat", fatTotal);

        return result;
    }

    @Override
    public Meal readByUserAndDateTime(AppUser user, LocalDateTime date) {
        return repo.getByUserAndDateTime(user, date);
    }

    @Override
    public List<String> datesOfMealsForUserForChart(AppUser user) {
        List<String> result = new ArrayList<>();
        Set<LocalDate> filtered = new HashSet<>();
        user.getMeals().forEach((item) -> {
            filtered.add(item.getConsDate().toLocalDate());
        });
        filtered.forEach((element) -> {
            result.add(element.format(DateTimeFormatter.ISO_DATE));
        });
        return result;
    }

    @Override
    public Map<String, Double> allTimeNutrientConsumption(AppUser user) {
        Map<String, Double> result = new HashMap<>();
        Double caloriesTot = 0.0;
        Double carbsTot = 0.0;
        Double protTot = 0.0;
        Double fatTot = 0.0;
        List<Meal> allTimeMeals = user.getMeals();

        for (Meal meal : allTimeMeals) {
            caloriesTot += meal.getCalories();
            carbsTot += meal.getCarbs();
            protTot += meal.getProtein();
            fatTot += meal.getFat();

        }
        result.put("cal", caloriesTot);
        result.put("carb", carbsTot);
        result.put("prot", protTot);
        result.put("fat", fatTot);

        return result;
    }

}
