package com.eggs.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy="menu", cascade=CascadeType.ALL)
    private Restaurant restaurant;

    @OneToMany(mappedBy="menu", cascade=CascadeType.ALL)
    private List<Food> foods = new ArrayList<Food>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
