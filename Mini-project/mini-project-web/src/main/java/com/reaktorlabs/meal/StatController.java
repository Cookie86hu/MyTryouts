/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.meal;

import com.reaktorlabs.chart.ChartController;
import com.reaktorlabs.model.meal.MealServiceInterface;
import com.reaktorlabs.model.user.AppUser;
import com.reaktorlabs.model.user.UserServiceInterface;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named(value = "statController")
@ViewScoped
public class StatController implements Serializable {

    private AppUser user;
    private HttpServletRequest req;
    private String selectedDate;
    private List<String> datesAll;
    private Map<String, Double> dailyNutrians;
    private UserServiceInterface userService;
    private MealServiceInterface mealService;

    public StatController() {
    }

    @Inject
    public StatController(UserServiceInterface userService, MealServiceInterface mealService,
            HttpServletRequest req) {
        this.userService = userService;
        this.mealService = mealService;
        this.req = req;
        this.datesAll = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        user = userService.readByEmail(req.getUserPrincipal().getName());
        getAllMealDates();
        selectedDate = datesAll.get(0);
        getNutriansForDate();
    }

    public void dateChangeListener() {
        getNutriansForDate();
        //chartCTRL.renderCharts();
    }

    private void getNutriansForDate() {
        dailyNutrians = mealService.dailyConsumtionOfNutrients(user, LocalDate.parse(selectedDate, DateTimeFormatter.ISO_DATE));
    }

    private void getAllMealDates() {
        datesAll = mealService.datesOfMealsForUserForChart(user);
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public List<String> getDatesAll() {
        return datesAll;
    }

    public void setDatesAll(List<String> datesAll) {
        this.datesAll = datesAll;
    }

    public Map<String, Double> getDailyNutrians() {
        return dailyNutrians;
    }

    public void setDailyNutrians(Map<String, Double> dailyNutrians) {
        this.dailyNutrians = dailyNutrians;
    }

}
