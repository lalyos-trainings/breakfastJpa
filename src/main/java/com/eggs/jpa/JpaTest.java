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
        test.listAllMenus();

    }

    private void listAllMenus() {
        List<Menu> resultList = em.createQuery("Select menu From Menu menu").getResultList();
        for (Menu menu : resultList) {
            System.out.format("=== Menu: %s%n", menu.getName());
            for (Food food : menu.getFoods()) {
                System.out.format("  [%3d] %-20s : %5.2f%n", food.getId(), food.getName(), food.getPrice());
            }
        }
        
    }

    private void createFirstMenu() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        
        Food f1 = new Food("bablevces", 480f);
        Food f2 = new Food("palacsinta", 320f);
        
        em.persist(f1);
        em.persist(f2);
        
        Menu menu = new Menu();
        menu.setName("karcsi menuje");
        menu.getFoods().add(f1);
        menu.getFoods().add(f2);
        em.persist(menu);
        
        transaction.commit();        
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
