package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class DB {

    public static void mostrarAnimales(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("stardamvalley");
        EntityManager entityManager = emf.createEntityManager();

        try{
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animales = query.getResultList();

            for (Animal a : animales) {
                System.out.println("ID: " + a.getId() + ", Nombre: " + a.getNombre() +
                        ", Dia Insecion: " + a.getDiaInsercion() + ", Peso: " + a.getPeso() +
                        ", Tipo: " + a.getTipo());
            }
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

    public static void insertarAnimal(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("stardamvalley");
        EntityManager entityManager = emf.createEntityManager();

        Scanner ent = new Scanner(System.in);
        double pesoF;

        System.out.println("Dame el nombre del animal: ");
        String nombre = ent.nextLine();
        System.out.println("Dame el peso del aninal: ");
        String peso = ent.nextLine();
        if(peso.equalsIgnoreCase("")){
            pesoF = 0;
        }else{
            pesoF = Double.parseDouble(peso);
        }
        System.out.println("Dame el tipo del animal: ");
        Tipo tipo = Tipo.valueOf(ent.nextLine());

        try{
            entityManager.getTransaction().begin();

            Animal a = new Animal(0, nombre, pesoF, tipo);

            entityManager.persist(a);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            entityManager.close();
            emf.close();
        }
    }

    public static void modificarAnimal(String id){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("stardamvalley");
        EntityManager entityManager = emf.createEntityManager();

        Scanner ent = new Scanner(System.in);

        try{
            entityManager.getTransaction().begin();

            Animal a = entityManager.find(Animal.class, Integer.parseInt(id));

            if(a != null){
                System.out.println("Dame el nuevo nombre del animal: ");
                a.setNombre(ent.nextLine());

                entityManager.merge(a);

                System.out.println("Animal modificado");
            }
            else{
                System.out.println("Animal no encontrado");
            }

            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            entityManager.close();
            emf.close();
        }
    }

    public static void eliminarAnimal(String id){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("stardamvalley");
        EntityManager entityManager = emf.createEntityManager();

        Scanner ent = new Scanner(System.in);

        try {
            entityManager.getTransaction().begin();

            Animal a = entityManager.find(Animal.class, Integer.parseInt(id));

            if (a != null) {
                entityManager.remove(a);

                System.out.println("Animal eliminado");
            }
            else{
                System.out.println("Animal no eliminado");
            }

            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            entityManager.close();
            emf.close();
        }
    }
}
