package com.reaktorlabs.model.food;

import com.reaktorlabs.model.meal.Meal;
import com.reaktorlabs.model.user.ALLERGIES;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
    @NamedQuery(name = "Food.findByName",
            query = "SELECT i FROM Food i WHERE i.name LIKE :name")
    ,
    @NamedQuery(name = "Food.findAll",
            query = "SELECT i FROM Food i")
})
public class Food implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Double calories;
    @NotNull
    private Double carb;
    @NotNull
    private Double prot;
    @NotNull
    private Double fat;

    @NotNull
    private Double amount;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<ALLERGIES> allergens;

    @ManyToMany(mappedBy = "foods", fetch = FetchType.EAGER)
    private List<Meal> mealsContainingThisFood;

    public Food() {
        this.allergens = new HashSet<>();
        this.calories = 0.0;
        this.carb = 0.0;
        this.prot = 0.0;
        this.fat = 0.0;
        this.amount = 1.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getCarb() {
        return carb;
    }

    public void setCarb(Double carb) {
        this.carb = carb;
    }

    public Double getProt() {
        return prot;
    }

    public void setProt(Double prot) {
        this.prot = prot;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Set<ALLERGIES> getAllergens() {
        return allergens;
    }

    public void setAllergens(Set<ALLERGIES> allergens) {
        this.allergens = allergens;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<Meal> getMealsContainingThisFood() {
        return mealsContainingThisFood;
    }

    public void setMealsContainingThisFood(List<Meal> mealsContainingThisFood) {
        this.mealsContainingThisFood = mealsContainingThisFood;
    }

}
