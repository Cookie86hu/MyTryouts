/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.food;

import com.reaktorlabs.model.food.Food;
import com.reaktorlabs.model.user.ALLERGIES;
import com.reaktorlabs.model.user.AppUser;
import com.reaktorlabs.model.user.UserServiceInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.Enumeration;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;

@Named(value = "itemChangeListener")
@ViewScoped
public class ItemChangeListener implements Serializable {

    private List<ALLERGIES> allergyList;
    private HttpServletRequest servletRequest;
    private UserServiceInterface userService;

    @Inject
    public ItemChangeListener(HttpServletRequest servletRequest, UserServiceInterface userService) {
        this.servletRequest = servletRequest;
        this.userService = userService;
        this.allergyList = new ArrayList<>();
    }

    public void handleSelect(SelectEvent event) {
        if (!isEdable((Food) event.getObject())) {
            FacesContext.getCurrentInstance()
                    .addMessage("selectedFood", new FacesMessage(FacesMessage.SEVERITY_WARN, "You cant eat this due to allergy", event.getObject().toString()));
        }

    }

    private Boolean isEdable(Food item) {
        getUserAllergies();
        int i = 0;
        Boolean flag = true;
        while (i < allergyList.size()) {
            if (!item.getAllergens().contains(allergyList.get(i))) {
                i++;
            } else {
                return false;
            }
        }
        return flag;
    }

    private void getUserAllergies() {
        if (servletRequest.getUserPrincipal() != null) {
            AppUser user = userService.readByEmail(servletRequest.getUserPrincipal().getName());
            allergyList.addAll(user.getAllergies());
        }
    }
}
