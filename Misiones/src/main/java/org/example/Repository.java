package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

import static org.example.Main.pedirDato;
import static org.example.Main.pedirNumero;

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

    public void anadirJugador(EntityManager entityManager){

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
    }

    public void actualizarJugador(EntityManager entityManager) {


        try{
            System.out.println("Dame el jugador a modificar(ID):");
            mostrarJugador(entityManager);
            Jugador j;
            int eleccion = pedirNumero();
            if (eleccion != -1){
                j = entityManager.find(Jugador.class, eleccion);
            }
            else{
                return;
            }
            if(j == null){
                System.out.println("Has seleccionado una opción no valida");
                return;
            }

            System.out.println("Dame el nuevo nombre del jugador: ");
            String nNombre = pedirDato();

            j.setNombre(nNombre);

            entityManager.getTransaction().begin();
            entityManager.persist(j);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void insertarMision(EntityManager entityManager) {

        try{
            System.out.println("Dame la descripción de la misión:");
            String desc = pedirDato();

            System.out.println("¿Qué recompensa quieres que tenga la misión?:");
            mostrarRecompensa(entityManager);
            Recompensa r;
            int eleccion = pedirNumero();
            if (eleccion != -1){
                r = entityManager.find(Recompensa.class, eleccion);
            }
            else {
                return;
            }
            if(r == null){
                System.out.println("Has seleccionado una opción no valida");
                return;
            }

            Mision m = new Mision(desc, r);

            entityManager.getTransaction().begin();
            entityManager.persist(m);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void asignarMision(EntityManager entityManager) {

        boolean enc = false;

        try{
            System.out.println("¿Qué jugador quieres asignar a la misión(ID)?");
            mostrarJugador(entityManager);
            Jugador j;
            int eleccion = pedirNumero();
            if (eleccion != -1){
                j = entityManager.find(Jugador.class, eleccion);
            }
            else{
                return;
            }
            if(j == null){
                System.out.println("Has seleccionado una opción no valida");
                return;
            }

            System.out.println("¿A qué misión quieres asignar el juagor(ID)?");
            mostrarMisiones(entityManager);
            Mision m;
            eleccion = pedirNumero();
            if (eleccion != -1){
                m = entityManager.find(Mision.class, eleccion);
            }
            else{
                return;
            }
            if(m == null){
                System.out.println("Has seleccionado una opción no valida");
                return;
            }

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
    }

    public void rechazarMision(EntityManager entityManager) {

        try{
            System.out.println("¿A qué misión quieres asignar el jugador(ID)?");

            Query query = entityManager.createNamedQuery("Mision.findAll");
            List<Mision> misiones = query.getResultList();

            for ( Mision m : misiones){
                if(m.getJugadores().size() != 0){
                    System.out.println("ID: " + m.getId() + ", Descripción: " + m.getDescripcion() + ", recompensa: " + m.getRecompensa().getNombre());
                }
            }
            Mision m;
            int eleccion = pedirNumero();
            if (eleccion != -1){
                m = entityManager.find(Mision.class, eleccion);
            }
            else{
                return;
            }
            if(m == null){
                System.out.println("Has seleccionado una opción no valida");
                return;
            }

            System.out.println("¿Qué jugador quieres que rechace la misión?");

            if(!m.getJugadores().isEmpty()){
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
    }

    public void modificarRecompensa(EntityManager entityManager){

        try{
            System.out.println("¿A qué misión quieres cambiar la recompensa(ID)?");
            mostrarMisiones(entityManager);
            Mision m;
            int eleccion = pedirNumero();
            if (eleccion != -1){
                m = entityManager.find(Mision.class, eleccion);
            }
            else{
                return;
            }
            if(m == null){
                System.out.println("Has seleccionado una opción no valida");
                return;
            }

            System.out.println("¿Qué recompensa quieres que tenga la misión?:");
            mostrarRecompensa(entityManager);
            Recompensa r;
            eleccion = pedirNumero();
            if (eleccion != -1){
                r = entityManager.find(Recompensa.class, eleccion);
            }
            else {
                return;
            }
            if(r == null){
                System.out.println("Has seleccionado una opción no valida");
                return;
            }

            m.setRecompensa(r);

            entityManager.getTransaction().begin();
            entityManager.persist(m);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarMisiones(EntityManager entityManager) {

        try{
            Query query = entityManager.createNamedQuery("Mision.findAll");
            List<Mision> misiones = query.getResultList();

            for ( Mision m : misiones){
                System.out.println("ID: " + m.getId() + ", Descripción: "
                        + m.getDescripcion() + ", recompensa: " + m.getRecompensa().getNombre());
                if(!m.getJugadores().isEmpty()){
                    System.out.print("    Jugadores asocidos: ");
                    for (Jugador j : m.getJugadores()){
                        System.out.print(j.getNombre() + " | ");
                    }
                    System.out.println();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarRecompensa(EntityManager entityManager){

        try{
            Query query = entityManager.createNamedQuery("Recompensa.findAll");
            List<Recompensa> recompensas = query.getResultList();

            for ( Recompensa r : recompensas){
                System.out.println("ID: " + r.getId() + ", Nombre: " + r.getNombre() + ", Tipo: " + r.getTipo());

                if(!r.getMisiones().isEmpty()){
                    System.out.print("    Misiones donde se puede conseguir: ");
                    for (Mision m : r.getMisiones()){
                        System.out.print(m.getDescripcion() + " | ");
                    }
                    System.out.println();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarJugador(EntityManager entityManager){

        try{
            Query query = entityManager.createNamedQuery("Jugador.findAll");
            List<Jugador> jugadores = query.getResultList();

            for ( Jugador j : jugadores){
                System.out.println("ID: " + j.getId() + ", Nombre: " + j.getNombre());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //------------------------------------EJERCICIO 2-------------------------------------------------------

    public void buscarMision(EntityManager entityManager, String palabra){

        try{
            String patron = "%" + palabra + "%";

            Query query = entityManager.createQuery("SELECT m FROM Mision m WHERE m.descripcion LIKE :patron", Mision.class);
            query.setParameter("patron", patron);
            List<Mision> misiones = query.getResultList();

            if (misiones.isEmpty()) {
                System.out.println("No se encontraron misiones con la palabra clave: " + palabra);
            } else {
                for (Mision m : misiones) {
                    System.out.println("ID: " + m.getId() + ", Descripción: " + m.getDescripcion() + ", Recompensa: " + m.getRecompensa().getNombre());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
