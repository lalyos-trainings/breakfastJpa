package com.eggs.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaTest {

    private EntityManager em;

    public JpaTest() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        em = factory.createEntityManager();
    }

    public static void main(String[] args) {
        JpaTest test = new JpaTest();
        test.persistFood();
    }

    private void persistFood() {
        Food food = getSampleFood();
        em.persist(food);
    }

    private Food getSampleFood() {
        Food food = new Food();
        food.setName("Kordon Blue");
        food.setPrice(980f);
        return food;
    }

}
