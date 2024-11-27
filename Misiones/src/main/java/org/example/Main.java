package org.example;

import java.util.Scanner;

public class Main {

    public static String pedirDato(){
        Scanner ent = new Scanner(System.in);
        return ent.nextLine();
    }

    public static void menu(){

        Repository r  = Repository.getInstance();

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
                    r.anadirJugador();
                    break;
                case "2":
                    r.actualizarJugador();
                    break;
                case "3":
                    r.insertarMision();
                    break;
                case "4":
                    r.asignarMision();
                    break;
                case "5":
                    r.rechazarMision();
                    break;
                case "6":
                    r.modificarRecompensa();
                    break;
                case "7":
                    r.mostrarMisiones();
                    break;
                case "8":
                    r.mostrarRecompensa();
                    break;
                case "9":
                    salir = true;
                    break;
                default:
                    System.out.println("ERROR. Esa opción no existe.");
            }
        }while(!salir);

        r.closeEntityManagerFactory();
    }

    public static void main(String[] args) {

        menu();
    }
}