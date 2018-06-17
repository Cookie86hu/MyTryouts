package com.reaktorlabs.model.meal;

import com.reaktorlabs.model.food.Food;
import com.reaktorlabs.model.user.AppUser;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
    @NamedQuery(name = "Meal.findByUser",
            query = "SELECT m FROM Meal m WHERE m.userMeal = :userMeal")
    ,
    @NamedQuery(name = "Meal.findAll",
            query = "SELECT m FROM Meal m")
})
public class Meal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private AppUser userMeal;

    @NotNull
    @Column(name = "cons_date")
    private LocalDateTime consDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "meal_foods", joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Food> foods;

    @NotNull
    private Double calories;
    
    @NotNull
    private Double carbs;
    
    @NotNull
    private Double protein;
    
    @NotNull
    private Double fat;

    public Meal() {
        this.foods = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getConsDate() {
        return consDate;
    }

    public void setConsDate(LocalDateTime date) {
        this.consDate = date;
    }

    public AppUser getUserMeal() {
        return userMeal;
    }

    public void setUserMeal(AppUser userMeal) {
        this.userMeal = userMeal;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.userMeal);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Meal other = (Meal) obj;
        if (!Objects.equals(this.userMeal, other.userMeal)) {
            return false;
        }
        return true;
    }

}
