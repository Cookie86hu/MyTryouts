package com.reaktorlabs.meal;

import com.reaktorlabs.model.food.Food;
import com.reaktorlabs.model.food.FoodServiceInterface;
import java.util.List;
import java.io.Serializable;
import java.util.stream.Collectors;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named(value = "foodAddToMealController")
@ViewScoped
public class FoodAddToMealController implements Serializable {

    private Food selectedFood;
    private FoodServiceInterface foodService;
    private MealController mealCTRL;
    private List<Food> foodsToSave;
    private HttpServletRequest req;

    public FoodAddToMealController() {
    }

    @Inject
    public FoodAddToMealController(FoodServiceInterface foodService, 
                                   HttpServletRequest req,
                                   MealController mealCTRL) {
        this.foodService = foodService;
        this.req = req;
        this.foodsToSave = mealCTRL.getFoodsInMeal();
    }

    public List<Food> complete(String query) {
        List<Food> list = foodService.getByNameList(query);

        for (int i = 0; i < foodsToSave.size(); i++) {
            String itemToFilter = foodsToSave.get(i).getName();
            list = list.stream().filter(f -> !f.getName().equalsIgnoreCase(itemToFilter))
                    .collect(Collectors.toList());
        }
        return list;
    }

    public Double adjustFoodCal() {
        if (selectedFood == null) {
            return null;
        }
        return selectedFood.getCalories() * selectedFood.getAmount();
    }

    public Double adjustFoodCarb() {
        if (selectedFood == null) {
            return null;
        }
        return selectedFood.getCarb() * selectedFood.getAmount();
    }

    public Double adjustFoodProt() {
        if (selectedFood == null) {
            return null;
        }
        return selectedFood.getProt() * selectedFood.getAmount();
    }

    public Double adjustFoodFat() {
        if (selectedFood == null) {
            return null;
        }
        return selectedFood.getFat() * selectedFood.getAmount();
    }

    public Food getSelectedFood() {
        return selectedFood;
    }

    public void setSelectedFood(Food selectedFood) {
        this.selectedFood = selectedFood;
    }

    public FoodServiceInterface getFoodService() {
        return foodService;
    }

    public void setFoodService(FoodServiceInterface foodService) {
        this.foodService = foodService;
    }

    public List<Food> getFoodsToSave() {
        return foodsToSave;
    }

    public void setFoodsToSave(List<Food> foodsToSave) {
        this.foodsToSave = foodsToSave;
    }

    public HttpServletRequest getReq() {
        return req;
    }

    public void setReq(HttpServletRequest req) {
        this.req = req;
    }

}
