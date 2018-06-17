package com.reaktorlabs.model.meal;

import com.reaktorlabs.model.food.Food;
import com.reaktorlabs.model.user.AppUser;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Meal.class)
public abstract class Meal_ {

	public static volatile SingularAttribute<Meal, AppUser> userMeal;
	public static volatile ListAttribute<Meal, Food> foods;
	public static volatile SingularAttribute<Meal, Double> carbs;
	public static volatile SingularAttribute<Meal, Double> protein;
	public static volatile SingularAttribute<Meal, Double> fat;
	public static volatile SingularAttribute<Meal, Long> id;
	public static volatile SingularAttribute<Meal, Double> calories;
	public static volatile SingularAttribute<Meal, LocalDateTime> consDate;

}

