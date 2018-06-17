/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.converter;

import com.reaktorlabs.model.food.Food;
import com.reaktorlabs.model.food.FoodServiceInterface;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter("foodConverter")
public class FoodConverter implements Converter {
    
    private FoodServiceInterface foodService;

    public FoodConverter() {
    }
    
    @Inject
    public FoodConverter(FoodServiceInterface foodService) {
        this.foodService = foodService;
        
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return foodService.readByName(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Food item = (Food) value;
        return item.getName();
    }
    
}
