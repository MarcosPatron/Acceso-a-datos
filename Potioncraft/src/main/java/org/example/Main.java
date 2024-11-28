package org.example;

import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class Main {

    public static String pedirDato(){
        Scanner ent = new Scanner(System.in);
        return ent.nextLine();
    }

    public static void menuInicio(){

        boolean salir = false;

        do{
            System.out.println("===== BIENVENIDO A POTIONCRAFT =====" +
                    "\n1. Nueva Partida" +
                    "\n2. Cargar Partida" +
                    "\n3. SALIR");

            switch (pedirDato()){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("ERROR");
            }
        }while(!salir);
    }

    public void menu(){
        boolean salir = false;

        Repository r  = Repository.getInstance();
        EntityManager entityManager = r.getEntityManager();

        do{
            System.out.println("===== POTIONCRAFT =====" +
                    "\n1. Crear Pociones" +
                    "\n2. Vender Pociones" +
                    "\n3. Comprar Ingredientes" +
                    "\n4. Mostrar Estadisticas" +
                    "\n5. Mostrar Comerciantes" +
                    "\n6. Mostrar Pociones" +
                    "\n7. SALIR");

            switch (pedirDato()){
                case "1":
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
                case "7":
                    salir = true;
                    break;
                default:
                    System.out.println("ERROR");
            }
        }while(!salir);
        
        entityManager.close();
        r.closeEntityManagerFactory();
    }

    public static void main(String[] args) {

    }
}