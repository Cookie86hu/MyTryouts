package com.reaktorlabs.model.food;

import com.reaktorlabs.model.meal.Meal;
import com.reaktorlabs.model.user.ALLERGIES;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Food.class)
public abstract class Food_ {

	public static volatile SingularAttribute<Food, Double> carb;
	public static volatile SingularAttribute<Food, Double> amount;
	public static volatile SingularAttribute<Food, Double> prot;
	public static volatile SingularAttribute<Food, String> name;
	public static volatile SingularAttribute<Food, Double> fat;
	public static volatile ListAttribute<Food, Meal> mealsContainingThisFood;
	public static volatile SingularAttribute<Food, Long> id;
	public static volatile SingularAttribute<Food, Double> calories;
	public static volatile SetAttribute<Food, ALLERGIES> allergens;

}

