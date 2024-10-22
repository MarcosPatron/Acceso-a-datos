package General;

import Utils.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Integer.parseInt;


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

    public static Granja nuevaPartida(){

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
        return PropertiesF.nuevaGranja();
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

        Granja g = new Granja(1000, Estaciones.Primavera);

        do{

            menuInicio();

            switch (eleccion()){
                case "1":
                    g = nuevaPartida();
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

        g.nuevoDia(semillas);
        do{

            menuFuncionalidades();
            switch (eleccion()) {
                case "1":
                    g.nuevoDia(semillas);
                    break;
                case "2":
                    Huerto.atendercCultivos(semillas, g);
                    break;
                case "3":
                    Huerto.mostrarHuerto();
                    g.getSemillasVenta().mostrarTienda();
                    System.out.println("¿En que posición quires plantar las semillas?");
                    Huerto.plantarSemillaColumna(semillas.get("Promavera").get(0), parseInt(eleccion()));
                    break;
                case "4":
                    g.setDinero(g.getDinero() + g.getAlmacen().venderCosecha());
                    break;
                case "5":
                    g.mostrarGranja();
                    break;
                case "6":
                    System.out.println("Saliendo...");
                    salir = true;
                    break;
                default:
                    System.out.println("ERROR. Esa elección no existe.");
            }
            if(!salir) {
                Huerto.mostrarHuerto();
            }
        }while(!salir);
        BinF.guardarPartida(g);
    }
}