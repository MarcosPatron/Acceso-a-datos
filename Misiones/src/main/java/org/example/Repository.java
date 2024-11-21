package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class Repository {

    private static Repository instance;
    private EntityManagerFactory entityManagerFactory;

    private Repository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("misiones");
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
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

    public void mostrarMisiones() {
        getInstance();
        EntityManager entityManager = getEntityManager();

        try{
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("SELECT m FROM mision m", Mision.class);
            List<Mision> misiones = query.getResultList();

            for ( Mision m : misiones){
                System.out.println("ID: " + m.getId() + ", Descripción: " + m.getDescripcion() + ", recompensa: " + m.getRecompensa());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            entityManager.close();
            closeEntityManagerFactory();
        }
    }
}
