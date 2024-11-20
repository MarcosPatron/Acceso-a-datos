package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean exit = false;
        Scanner ent = new Scanner(System.in);

        do {
            System.out.println("STARDAM VALLEY" +
                    "\n1. Consultar animales." +
                    "\n2. Insertar animal." +
                    "\n3. Actualizar animal." +
                    "\n4. Eliminar animal." +
                    "\n5. Salir.");
            switch (ent.nextLine()) {
                case "1":
                    DB.mostrarAnimales();
                    break;
                case "2":
                    DB.insertarAnimal();
                    break;
                case "3":
                    System.out.println("Dame el ID del animal a modificar: ");
                    DB.modificarAnimal(ent.nextLine());
                    break;
                case "4":
                    System.out.println("Dame el ID del animal a modificar: ");
                    DB.eliminarAnimal(ent.nextLine());
                    break;
                case "5":
                    exit = true;
                    break;
                default:
                    System.out.println("ERROR");
            }
        } while (!exit);
    }
}