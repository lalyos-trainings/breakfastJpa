package com.eggs.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    private EntityManager em;

    public JpaTest() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        em = factory.createEntityManager();
    }

    public static void main(String[] args) {
        JpaTest test = new JpaTest();
        //test.persistFood();
        //test.removeFood();
        //test.listFoods();
        //test.createFirstMenu();
        //test.createSecondMenu();
        test.listAllMenus();

    }

    private void listAllMenus() {
        List<Menu> resultList = em.createQuery("Select menu From Menu menu").getResultList();
        for (Menu menu : resultList) {
            System.out.format("=== Rest: %s%n", menu.getRestaurant());
            for (Food food : menu.getFoods()) {
                System.out.format("  [%3d] %-20s : %5.2f%n", food.getId(), food.getName(), food.getPrice());
            }
        }
    }

    private void createFirstMenu() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Menu menu = new Menu();
        menu.setRestaurant(getFirstRestaurant(menu));
        
        addFoodToMenu(menu, "bablevces", 480f);
        addFoodToMenu(menu,"palacsinta", 320f);        
        
        em.persist(menu);    
        transaction.commit();
        em.refresh(menu);
    }

    private Restaurant getFirstRestaurant(Menu menu) {
        Address a = new Address("Futop utca 47", "Budapest", "1056");
        Restaurant rest = new Restaurant();
        rest.setAddress(a);
        rest.setName("Karcsi Bufeje");
        rest.setMenu(menu);
        return rest;
    }

    private void addFoodToMenu(Menu menu, String foodName, float price) {
        Food f1 = new Food(foodName, price);
        f1.setMenu(menu);
        em.persist(f1);
    }

    private void createSecondMenu() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Menu menu = new Menu();
        menu.setRestaurant(getSecondRestaurant(menu));

        addFoodToMenu(menu,"pizza Margherita", 1200f);
        addFoodToMenu(menu,"grilled cat", 2100f);
        addFoodToMenu(menu,"ostryga", 4200f);
        
        em.persist(menu);
        transaction.commit();
        em.refresh(menu);

    }

    private Restaurant getSecondRestaurant(Menu menu) {
        Address a = new Address("Kalvaria Ter 11", "Budapest", "1088");
        Restaurant rest = new Restaurant();
        rest.setAddress(a);
        rest.setName("Marcello");
        rest.setMenu(menu);
        return rest;
    }

    private void removeFood() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Food food = em.find(Food.class, 151l);
        em.remove(food);
        transaction.commit();
    }

    private void listFoods() {
        
        List<Food> resultList = em.createQuery("SELECT f from Food f ").getResultList();
        for (Food food : resultList) {
            System.out.println(food);
        }
        
    }

    private void persistFood() {
        
        Food food = getSampleFood();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(food);
        transaction.commit();
    }

    private Food getSampleFood() {
        Food food = new Food();
        food.setName("Kordon Blue");
        food.setPrice(980f);
        return food;
    }

}
