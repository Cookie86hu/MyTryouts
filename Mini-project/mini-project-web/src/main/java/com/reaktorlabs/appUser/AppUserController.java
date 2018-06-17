package com.reaktorlabs.appUser;

import com.reaktorlabs.model.food.Food;
import com.reaktorlabs.model.meal.Meal;
import com.reaktorlabs.model.user.ALLERGIES;
import com.reaktorlabs.model.user.AppUser;
import com.reaktorlabs.model.user.UserServiceInterface;
import java.util.Arrays;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


@Named(value="appUserController")
@RequestScoped
public class AppUserController {
    
    private AppUser user;
    private Food food;
    private Meal meal;
    private ALLERGIES[] allergyList;
 
    private UserServiceInterface userService;
    
    

    @Inject
    public AppUserController(UserServiceInterface userService) {
        this.user = new AppUser();
        this.food = new Food();
        this.meal = new Meal();
        this.userService = userService;
        
    }

    public AppUserController() {
        
    }
    
    
    public void createNewUser(){
        loadAllergies();
        userService.add(user);
        handleWelcome();
        clear();
        
    }

    public UserServiceInterface getUserService() {
        return userService;
    }
    
    private void clear(){
        user = new AppUser();
        allergyList = new ALLERGIES[4];
    }
    
    private void loadAllergies(){
        user.getAllergies().addAll(Arrays.asList(allergyList));
    }
    
    private void handleWelcome(){
        FacesContext.getCurrentInstance()
                    .addMessage("new-user",new FacesMessage("Thank you for Registering " + user.getName() + "! Please sign in!"));
         }

    public void setUserService(UserServiceInterface userService) {
        this.userService = userService;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public ALLERGIES[] getAllergyList() {
        return allergyList;
    }

    public void setAllergyList(ALLERGIES[] allergyList) {
        this.allergyList = allergyList;
    }
    
    
}
