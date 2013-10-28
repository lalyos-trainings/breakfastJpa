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
        test.listFoods();
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
