package org.example;

import jakarta.persistence.EntityManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static String pedirDato(){
        Scanner ent = new Scanner(System.in);
        return ent.nextLine();
    }

    public static int pedirNumero(){

        Scanner ent = new Scanner(System.in);
        int num;

        try{
            num = ent.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Debes introducir un numero.");
            num = -1;
        }
        return num;
    }

    public static void menu(){

        Repository r  = Repository.getInstance();
        EntityManager entityManager = r.getEntityManager();

        Scanner ent = new Scanner(System.in);
        boolean salir = false;

        do{
            System.out.println("-----MENU DE MISIONES-----" +
                    "\n1. Añadir jugador." +
                    "\n2. Actulizar jugador." +
                    "\n3. Insertar misión." +
                    "\n4. Asignar misión." +
                    "\n5. Rechazar misión." +
                    "\n6. Modifcar recompensa misión." +
                    "\n7. Consultar misiones." +
                    "\n8. Consultar recompensas." +
                    "\n9. SALIR.");
            switch (ent.nextLine()){
                case "1":
                    r.anadirJugador(entityManager);
                    break;
                case "2":
                    r.actualizarJugador(entityManager);
                    break;
                case "3":
                    r.insertarMision(entityManager);
                    break;
                case "4":
                    r.asignarMision(entityManager);
                    break;
                case "5":
                    r.rechazarMision(entityManager);
                    break;
                case "6":
                    r.modificarRecompensa(entityManager);
                    break;
                case "7":
                    r.mostrarMisiones(entityManager);
                    break;
                case "8":
                    r.mostrarRecompensa(entityManager);
                    break;
                case "9":
                    salir = true;
                    break;
                default:
                    System.out.println("ERROR. Esa opción no existe.");
            }
        }while(!salir);

        entityManager.close();
        r.closeEntityManagerFactory();
    }

    public static void nuevoMenu(){

        boolean salir = false;
        Repository r  = Repository.getInstance();
        EntityManager entityManager = r.getEntityManager();

        do{
            System.out.println("-----MENU-----" +
                    "\n1. Buscar misiones por palabra." +
                    "\n2. Buscar recompensas por tipo." +
                    "\n3. Buscar jugadro por ID." +
                    "\n4. Buscar mison por tipo recompensa." +
                    "\n5. Buscar jugdores con mision." +
                    "\n6. Salir.");

            switch (pedirDato()){
                case "1":
                    System.out.println("Por que palabra quieres buscar: ");
                    r.buscarMision(entityManager, pedirDato());
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                default:
                    System.out.println("ERROR");
            }
        }while(!salir);

        entityManager.close();
        r.closeEntityManagerFactory();
    }

    public static void main(String[] args) {

        nuevoMenu();
    }
}