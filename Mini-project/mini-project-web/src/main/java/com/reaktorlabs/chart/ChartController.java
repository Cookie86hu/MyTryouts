/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.chart;

import com.reaktorlabs.model.meal.MealServiceInterface;
import com.reaktorlabs.model.user.AppUser;
import com.reaktorlabs.model.user.UserServiceInterface;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.chart.PieChartModel;

@Named(value = "chartController")
@ViewScoped
public class ChartController implements Serializable {

    private Double avgCalories;
    private MealServiceInterface mealService;
    private UserServiceInterface userService;
    private Map<String, Double> allTimeNutrians;
    private AppUser user;
    private HttpServletRequest req;

    public ChartController() {
    }

    @Inject
    public ChartController(MealServiceInterface mealService,
            HttpServletRequest req, UserServiceInterface userService) {
        this.mealService = mealService;
        this.userService = userService;
        this.req = req;
        this.user = userService.readByEmail(req.getUserPrincipal().getName());

    }

    public PieChartModel createModel1(String date) {
        PieChartModel model1 = new PieChartModel();
        LocalDate usefull = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        Map<String, Double> dateNutrians
                = mealService.dailyConsumtionOfNutrients(user, usefull);

        model1.set("Carbs", dateNutrians.get("carb"));
        model1.set("Protein", dateNutrians.get("prot"));
        model1.set("Fat", dateNutrians.get("fat"));

        model1.setTitle("Daily Statistics");
        model1.setLegendPosition("e");
        model1.setFill(false);
        model1.setShowDataLabels(true);
        model1.setDiameter(150);

        return model1;
    }

    @PostConstruct
    public void init() {
        allTimeNutrians = allTimeConsumtion();
        avgCalories = allTimeNutrians.get("cal") 
                / mealService.datesOfMealsForUserForChart(user).size();
    }

    public PieChartModel createModelAllTime() {
        PieChartModel modelAllTime = new PieChartModel();
        Map<String, Double> nutrians = allTimeConsumtion();

        modelAllTime.set("Carbs", nutrians.get("carb"));
        modelAllTime.set("Protein", nutrians.get("prot"));
        modelAllTime.set("Fat", nutrians.get("fat"));

        modelAllTime.setTitle("All time");
        modelAllTime.setLegendPosition("e");
        modelAllTime.setFill(false);
        modelAllTime.setShowDataLabels(true);
        modelAllTime.setDiameter(150);

        return modelAllTime;
    }

    private Map<String, Double> allTimeConsumtion() {
        return mealService.allTimeNutrientConsumption(user);
    }

    public Double getAvgCalories() {
        return avgCalories;
    }

    public void setAvgCalories(Double avgCalories) {
        this.avgCalories = avgCalories;
    }

}
