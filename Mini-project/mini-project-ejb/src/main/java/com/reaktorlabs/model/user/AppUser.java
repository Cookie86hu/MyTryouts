package com.reaktorlabs.model.user;

import com.reaktorlabs.logic.util.RegexConstants;
import com.reaktorlabs.model.meal.Meal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
    @NamedQuery(name="AppUser.findById",
                query="SELECT u FROM AppUser u WHERE u.id = :id"),
    @NamedQuery(name="AppUser.findByEmail",
                query="SELECT u FROM AppUser u WHERE u.email = :email"),
    @NamedQuery(name="AppUser.findAll",
                query="SELECT u FROM AppUser u")
}) 
public class AppUser implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "app_user_role")
    private String appUserRole;
    
    @NotNull
    @Pattern(regexp = RegexConstants.NAME)
    @Size(min = 3, max = 250)
    private String name;
    
    @NotNull
    @Pattern(regexp = RegexConstants.EMAIL)
    @Size(max = 250)
    private String email;
    
    @NotNull
    @Size(min = 5, max = 250)
    private String password;
   
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<ALLERGIES> allergies;
    
    @OneToMany(mappedBy = "userMeal", fetch = FetchType.EAGER)
    private List<Meal> meals;
  
    @Column(name = "limit_kcal")
    private Double limitKcal;
    @Column(name = "limit_prot")
    private Double limitProt;
    @Column(name = "limit_carb")
    private Double limitCarb;
    @Column(name = "limit_fat")
    private Double limitFat;

    public AppUser() {
        
        this.allergies = new HashSet<>();
        this.meals = new ArrayList<>();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(String appUserRole) {
        this.appUserRole = appUserRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ALLERGIES> getAllergies() {
        return allergies;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public void setAllergies(Set<ALLERGIES> allergies) {
        this.allergies = allergies;
    }

    public Double getLimitKcal() {
        return limitKcal;
    }

    public void setLimitKcal(Double limitKcal) {
        this.limitKcal = limitKcal;
    }

    public Double getLimitProt() {
        return limitProt;
    }

    public void setLimitProt(Double limitProt) {
        this.limitProt = limitProt;
    }

    public Double getLimitCarb() {
        return limitCarb;
    }

    public void setLimitCarb(Double limitCarb) {
        this.limitCarb = limitCarb;
    }

    public Double getLimitFat() {
        return limitFat;
    }

    public void setLimitFat(Double limitFat) {
        this.limitFat = limitFat;
    }
 
}
