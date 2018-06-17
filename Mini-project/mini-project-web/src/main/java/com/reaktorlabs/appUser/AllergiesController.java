/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.appUser;

import com.reaktorlabs.model.user.ALLERGIES;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name ="allergiesController")
@RequestScoped
public class AllergiesController {
    
    

    public AllergiesController() {
        
    }
    
    public ALLERGIES[] getAllergies(){
        return ALLERGIES.values();
    }
    
    public ALLERGIES getByString(String value){
       
        return ALLERGIES.valueOf(value);
    }

    
}
