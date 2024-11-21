package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Gestion {

    private static Gestion instance;
    private EntityManagerFactory entityManagerFactory;

    private Gestion() {
        entityManagerFactory = Persistence.createEntityManagerFactory("misiones");
    }

    public static Gestion getInstance() {
        if (instance == null) {
            instance = new Gestion();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void closeEntityManagerFactory() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
