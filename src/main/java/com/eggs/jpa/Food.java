package com.eggs.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Food {

    public Food() {
        super();
    }

    public Food(String name, Float price) {
        super();
        this.name = name;
        this.price = price;
    }
    
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private Menu menu;
    
    private String name;
    private Float price;
    
    @Override
    public String toString() {
        return "Food [id=" + id + ", name=" + name + ", price=" + price + "]";
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
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

}
