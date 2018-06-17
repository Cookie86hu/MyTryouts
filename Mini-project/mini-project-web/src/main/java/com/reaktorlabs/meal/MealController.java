package com.reaktorlabs.meal;

import com.reaktorlabs.model.food.Food;
import com.reaktorlabs.model.food.FoodServiceInterface;
import com.reaktorlabs.model.meal.Meal;
import com.reaktorlabs.model.meal.MealServiceInterface;
import com.reaktorlabs.model.user.AppUser;
import com.reaktorlabs.model.user.UserServiceInterface;
import java.util.List;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named(value = "mealController")
@ViewScoped
public class MealController implements Serializable {

    private Meal meal;
    private MealServiceInterface mealService;
    private List<Food> foodsInMeal;
    private FoodAddToMealController foodCTRL;
    private UserServiceInterface userService;
    private HttpServletRequest req;
    private Date date;

    public MealController() {
    }

    @Inject
    public MealController(FoodServiceInterface foodService,
            MealServiceInterface mealService,
            FoodAddToMealController foodCTRL,
            UserServiceInterface userService,
            HttpServletRequest req) {
        this.mealService = mealService;
        this.userService = userService;
        this.foodCTRL = foodCTRL;
        this.req = req;
        this.foodsInMeal = new ArrayList();

        this.meal = new Meal();
    }

    public void addFoodToSaveList(Food selectedFood) {
        foodsInMeal.add(selectedFood);
    }

    public Double calcTotalCalories() {
        Double result = 0.0;
        for (Food item : foodsInMeal) {
            result += item.getCalories() * item.getAmount();
        }
        meal.setCalories(result);
        return result;
    }

    public Double calcTotalCarbs() {
        Double result = 0.0;
        for (Food item : foodsInMeal) {
            result += item.getCarb() * item.getAmount();
        }
        meal.setCarbs(result);
        return result;
    }

    public Double calcTotalProtein() {
        Double result = 0.0;
        for (Food item : foodsInMeal) {
            result += item.getProt() * item.getAmount();
        }
        meal.setProtein(result);
        return result;
    }

    public Double calcTotalFat() {
        Double result = 0.0;
        for (Food item : foodsInMeal) {
            result += item.getFat() * item.getAmount();
        }
        meal.setFat(result);
        return result;
    }

    public String createNewMeal() {

        AppUser user = userService.readByEmail(req.getUserPrincipal().getName());
        meal.setUserMeal(user);
        meal.setFoods(foodsInMeal);
        meal.setConsDate(convertDate());
        mealService.add(meal);
        addMsg();
        resetList();
        return "";
    }

    private void resetList() {
        this.foodsInMeal = new ArrayList();
        this.date = null;
        foodCTRL.getFoodsToSave().clear();
        foodCTRL.setSelectedFood(null);
        this.meal = new Meal();
    }
    
    private void addMsg(){
        FacesContext.getCurrentInstance()
                    .addMessage("submit-id", new FacesMessage("New meal added"));
    }

    private LocalDateTime convertDate() {
        if (date == null) {
            return LocalDateTime.now();
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Food> getFoodsInMeal() {
        return foodsInMeal;
    }

    public void setFoodsInMeal(List<Food> foodsInMeal) {
        this.foodsInMeal = foodsInMeal;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public MealServiceInterface getMealService() {
        return mealService;
    }

    public void setMealService(MealServiceInterface mealService) {
        this.mealService = mealService;
    }

}
