package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class DB {

    public static void mostrarAnimales(){

        System.out.println(System.getProperty("java.class.path"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia-tienda");
        EntityManager entityManager = emf.createEntityManager();

        try{
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("SELECT a FROM Animales a", Animal.class);
            List<Animal> animales = query.getResultList();

            for (Animal a : animales) {
                System.out.println("ID: " + a.getId() + ", Nombre: " + a.getNombre() +
                        ", Dia Insecion: " + a.getDiaInsercion() + ", Peso: " + a.getPeso() +
                        ", Tipo: " + a.getTipo());
            }

            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally {
            entityManager.close();
            emf.close();
        }
    }



}
