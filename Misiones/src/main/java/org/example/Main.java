package org.example;

import java.util.Scanner;

public class Main {

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
                    "\n6. Modiifcar recompensa misión." +
                    "\n7. Consultar misiones." +
                    "\n8. Consultar recompensas." +
                    "\n9. SALIR.");
            switch (ent.nextLine()){
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
                    r.mostrarMisiones();
                    break;
                case "8":

                    break;
                case "9":

                    break;
                default:
                    System.out.println("ERROR. Esa opción no existe.");
            }
        }while(!salir);
    }

    public static void main(String[] args) {

        menu();
    }
}