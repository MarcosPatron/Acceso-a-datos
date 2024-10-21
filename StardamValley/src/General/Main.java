package General;

import Utils.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Main {


    public static String eleccion(){

        Scanner ent = new Scanner(System.in);
        String sal;

        try{

            sal = ent.nextLine();
        } catch (InputMismatchException e) {
            sal = "";
            throw new RuntimeException(e);
        }

        return sal;
    }

    public static void menuInicio(){

        Path path = Paths.get(Constantes.STARDAM_VALLEY);

        System.out.println("BINEVENIDO A STARDAM VALLEY" +
                "\n-----------------------------------" +
                "\n1. NUEVA PARTIDA");

        if(Files.exists(path)){
            System.out.println("2. CARGAR PARTIDA");
        }
    }

    public static void menuFuncionalidades(){
        System.out.println("STARDAM VALLEY" +
                "\n---------------------------------------" +
                "\n1. INICIAR NUEVO DIA" +
                "\n2. ATENDER CULTIVOS" +
                "\n3. PLANTAR CULTIVOS EN COLUMNA" +
                "\n4. VENDER COSECHA" +
                "\n5. MOSTRAR INFORMACION DE LA GRANJA" +
                "\n6. SALIR");
    }

    public static void nuevaPartida(){

        String resp;

        PropertiesF.crearFichero(Constantes.STARDAM_VALLEY);

        System.out.println("¿Quieres cambiar la configaración de tu partida?");
        resp = eleccion();

        if(resp.equalsIgnoreCase("si")){
            PropertiesF.setPropiedades();
        }
        else {
            PropertiesF.inicializarPropiedades();
        }

        Huerto.crearHuerto();
    }

    public static void cargarPartida(){

    }

    public static void iniciarJuego(){



    }

    public static void main(String[] args) {

        Map<String, ArrayList<Semilla>> semillas = new HashMap<>();
        Boolean salir = false;

        XMLFile.cargarSemillas(semillas);
        PropertiesF.inicializarPropiedades();

        Granja j;

        do{

            menuInicio();

            switch (eleccion()){
                case "1":
                    nuevaPartida();
                    salir = true;
                    break;
                case "2":
                    g = BinF.cargarGranja();
                    salir = true;
                    break;
                default:
                    System.out.println("ERROR. Esa elección no existe.");
            }
        }while(!salir);

        salir = false;

        do{

            menuFuncionalidades();
            switch (eleccion()) {
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
                default:
                    System.out.println("ERROR. Esa elección no existe.");
            }
        }while(!salir);
    }
}