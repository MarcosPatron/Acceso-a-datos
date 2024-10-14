package General;

import Utils.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static Utils.Escritura.escribir;
import static Utils.General.crearFichero;
import static Utils.Lectura.leer;

public class Main {

    public static void alta(ArrayList<Deportista> d){

        Scanner ent = new Scanner(System.in);
        String dni, nombre, fechanac;

        System.out.println("Dame el DNI del nuevo deportista: ");
        dni = ent.nextLine();
        System.out.println("Dame el nombre del nuevo deportista: ");
        nombre = ent.nextLine();
        System.out.println("Dame la fecha de nacimiento del nuevo deportista: ");
        fechanac = ent.nextLine();

        d.add(new Deportista(dni, nombre, fechanac, Deporte.Remo));
    }

    public static void baja(ArrayList<Deportista> d){

        Scanner ent = new Scanner(System.in);
        boolean enc = false;
        String dni;

        System.out.println("Dame el DNI del deportista que quieres dar de baja: ");
        dni = ent.nextLine();

        for(int i = 0; i < d.size() && !enc; i++){

            if(dni.equals(d.get(i).getDni())){
                d.remove(i);
            }
        }

        if(!enc){
           System.out.println("No se ha encontrado al deportista");
        }
        else{
            System.out.println("Deportista añadido.");
        }
    }

    public static void modificar(ArrayList<Deportista> d){

        Scanner ent = new Scanner(System.in);
        boolean enc = false;
        String dni;

        System.out.println("Dame el DNI del deportista que quieres modificar: ");
        dni = ent.nextLine();

        for(int i = 0; i < d.size() && !enc; i++){

            if(dni.equals(d.get(i).getDni())){
                d.get(i).setNombre("Pepito");
            }
        }

        if(!enc){
            System.out.println("No se ha encontrado al deportista");
        }
        else{
            System.out.println("Deportista modificado.");
        }
    }

    public static void alfabetico(ArrayList<Deportista> d){

        d.sort((d1, d2) -> d1.getNombre().compareTo(d2.getNombre()));

        for (Deportista deportista : d) {
            System.out.println(deportista);
        }
    }

    public static void nacimiento(ArrayList<Deportista> d){

        d.sort((d1, d2) -> d1.getFechaNac().compareTo(d2.getFechaNac()));

        for (Deportista deportista : d) {
            System.out.println(deportista);
        }
    }

    public static void main(String[] args) {

        boolean salir = false;
        Scanner ent = new Scanner(System.in);
        ArrayList<Deportista> deportistas = new ArrayList<>();

        /*
        Deportista d1 = new Deportista("76049078S", "Marcos", "2000-02-26", Deporte.Natacion);
        Deportista d2 = new Deportista("76053466D", "Pablo", "2003-12-26", Deporte.Waterpolo);
        Deportista d3 = new Deportista("76547783F", "Pedro", "1998-03-09", Deporte.Remo);
        Deportista d4 = new Deportista("76576543G", "Maria", "1993-06-19", Deporte.Vela);

        deportistas.add(d1);
        deportistas.add(d2);
        deportistas.add(d3);
        deportistas.add(d4);
        */

        crearFichero();
        deportistas = leer();


        do{

            System.out.println("-----MENU-----" +
                    "\n1. Alta" +
                    "\n2. Baja" +
                    "\n3. Modificacion de datos" +
                    "\n4. Lista por orden alfabetico" +
                    "\n5. Lista por orden de edad" +
                    "\n6. Salir");

            switch(ent.nextInt()) {
                case 1:
                    alta(deportistas);
                    break;
                case 2:
                    baja(deportistas);
                    break;
                case 3:
                    modificar(deportistas);
                    break;
                case 4:
                    alfabetico(deportistas);
                    break;
                case 5:
                    nacimiento(deportistas);
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("ERROR. Esa opcion no existe");
            }
        }while(!salir);

        Escritura.escribir(deportistas);
    }
}