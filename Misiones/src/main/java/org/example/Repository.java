package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

import static org.example.Main.pedirDato;

public class Repository {

    private static Repository instance;
    private static EntityManagerFactory entityManagerFactory;

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

    public void anadirJugador(){
        getInstance();
        EntityManager entityManager = getEntityManager();

        try{
            System.out.println("Dame el nombre del jugador: ");
            String nombre = pedirDato();

            Jugador j = new Jugador(nombre);

            entityManager.getTransaction().begin();
            entityManager.persist(j);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            entityManager.close();
        }
    }

    public void actualizarJugador() {

        getInstance();
        EntityManager entityManager = getEntityManager();

        try{
            System.out.println("Dame el jugador a modificar(ID):");
            mostrarJugador();
            Jugador j = entityManager.find(Jugador.class, Integer.parseInt(pedirDato()));

            System.out.println("Dame el nombre del jugador: ");
            String nNombre = pedirDato();

            j.setNombre(nNombre);

            entityManager.getTransaction().begin();
            entityManager.persist(j);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            entityManager.close();
        }
    }

    public void insertarMision() {

        getInstance();
        EntityManager entityManager = getEntityManager();

        try{
            System.out.println("Dame la descripción de la misión:");
            String desc = pedirDato();

            System.out.println("¿Qué recompensa quieres que tenga la misión?:");
            mostrarRecompensa();
            Recompensa r = entityManager.find(Recompensa.class, Integer.parseInt(pedirDato()));

            Mision m = new Mision(desc, r);

            entityManager.getTransaction().begin();
            entityManager.persist(m);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            entityManager.close();
        }
    }

    public void asignarMision() {

        getInstance();
        EntityManager entityManager = getEntityManager();

        boolean enc = false;

        try{
            System.out.println("¿Qué jugador quieres asignar a la misión(ID)?");
            mostrarJugador();
            Jugador j = entityManager.find(Jugador.class, Integer.parseInt(pedirDato()));

            System.out.println("¿A qué misión quieres asignar el juagor(ID)?");
            mostrarMisiones();
            Mision m = entityManager.find(Mision.class, Integer.parseInt(pedirDato()));

            // Compruebo si el jugador seleccionado ya ha sido introfucido en la misión
            for (int i = 0; i < m.getJugadores().size() && !enc; i++) {
                 if(m.getJugadores().get(i).getId() == j.getId()){
                     enc = true;
                 }
            }

            if(!enc){
                m.getJugadores().add(j);
            }
            else{
                System.out.println("Este jugador ya ha sido asigando a esta misión");
            }


            entityManager.getTransaction().begin();
            entityManager.persist(m);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            entityManager.close();
        }
    }

    public void rechazarMision() {
        getInstance();
        EntityManager entityManager = getEntityManager();

        try{

            System.out.println("¿A qué misión quieres asignar el juagor(ID)?");
            mostrarMisiones();
            Mision m = entityManager.find(Mision.class, Integer.parseInt(pedirDato()));

            System.out.println("¿Qué jugador quieres que rechace la misión?");

            if(m.getJugadores().size() > 0){
                for (int i = 1; i <= m.getJugadores().size(); i++) {

                    System.out.println(i + ". ID: " + m.getJugadores().get(i-1).getId() + ", Nombre: " + m.getJugadores().get(i-1).getNombre());
                }
            }else{
                System.out.println("No hay jugadores asignados a esta misión");
            }
            m.getJugadores().remove(Integer.parseInt(pedirDato()) - 1);

            entityManager.getTransaction().begin();
            entityManager.persist(m);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            entityManager.close();
        }
    }

    public void modificarRecompensa(){
        getInstance();
        EntityManager entityManager = getEntityManager();

        try{
            System.out.println("¿A qué misión quieres cambiar la recompensa(ID)?");
            mostrarMisiones();
            Mision m = entityManager.find(Mision.class, Integer.parseInt(pedirDato()));

            System.out.println("¿Qué recompensa quieres que tenga la misión?:");
            mostrarRecompensa();
            Recompensa r = entityManager.find(Recompensa.class, Integer.parseInt(pedirDato()));

            m.setRecompensa(r);

            entityManager.getTransaction().begin();
            entityManager.persist(m);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
            finally {
            entityManager.close();
        }
    }

    public void mostrarMisiones() {
        getInstance();
        EntityManager entityManager = getEntityManager();

        try{
            entityManager.getTransaction().begin();

            Query query = entityManager.createNamedQuery("Mision.findAll");
            List<Mision> misiones = query.getResultList();

            for ( Mision m : misiones){
                System.out.println("ID: " + m.getId() + ", Descripción: " + m.getDescripcion() + ", recompensa: " + m.getRecompensa().getNombre());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            entityManager.close();
        }
    }

    public void mostrarRecompensa(){
        getInstance();
        EntityManager entityManager = getEntityManager();

        try{
            entityManager.getTransaction().begin();

            Query query = entityManager.createNamedQuery("Recompensa.findAll");
            List<Recompensa> recompensas = query.getResultList();

            for ( Recompensa r : recompensas){
                System.out.println("ID: " + r.getId() + ", Nombre: " + r.getNombre() + ", Tipo: " + r.getTipo());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            entityManager.close();
        }
    }

    public void mostrarJugador(){
        getInstance();
        EntityManager entityManager = getEntityManager();

        try{
            entityManager.getTransaction().begin();

            Query query = entityManager.createNamedQuery("Jugador.findAll");
            List<Jugador> jugadores = query.getResultList();

            for ( Jugador j : jugadores){
                System.out.println("ID: " + j.getId() + ", Nombre: " + j.getNombre());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            entityManager.close();
        }
    }
}
