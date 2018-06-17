/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.food;

import com.reaktorlabs.model.food.Food;
import com.reaktorlabs.model.food.FoodServiceInterface;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "foodSearchController")
@ViewScoped
public class FoodSearchController implements Serializable {

    private Food selectedFood;
    private FoodServiceInterface foodService;

    @Inject
    public FoodSearchController(FoodServiceInterface foodService) {
        this.foodService = foodService;
        
     // this.foodService.fillDB(); ONLY IF DB GETS DELETED!!!!
    }

    public FoodSearchController() {

    }

    public List<Food> complete(String query) {
        List<Food> list = foodService.getByNameList(query);
        return list;

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

}
