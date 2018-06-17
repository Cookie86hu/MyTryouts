package com.reaktorlabs.model.user;

import com.reaktorlabs.model.meal.Meal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AppUser.class)
public abstract class AppUser_ {

	public static volatile SetAttribute<AppUser, ALLERGIES> allergies;
	public static volatile SingularAttribute<AppUser, String> password;
	public static volatile SingularAttribute<AppUser, String> appUserRole;
	public static volatile SingularAttribute<AppUser, Double> limitFat;
	public static volatile SingularAttribute<AppUser, Double> limitProt;
	public static volatile SingularAttribute<AppUser, String> name;
	public static volatile SingularAttribute<AppUser, Double> limitCarb;
	public static volatile SingularAttribute<AppUser, Double> limitKcal;
	public static volatile SingularAttribute<AppUser, Long> id;
	public static volatile SingularAttribute<AppUser, String> email;
	public static volatile ListAttribute<AppUser, Meal> meals;

}

