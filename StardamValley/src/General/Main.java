package General;

import Utils.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Main {


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

        Scanner ent = new Scanner(System.in);
        String resp;

        PropertiesF.crearFichero(Constantes.STARDAM_VALLEY);

        System.out.println("¿Quieres cambiar la configaración de tu partida?");
        resp = ent.nextLine();

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

    public static void main(String[] args) {

        Map<String, ArrayList<Semilla>> semillas = new HashMap<>();
        Granja g = new Granja(1000, Estaciones.Primavera, new String[3], new Almacen());

        XMLFile.cargarSemillas(semillas);

        PropertiesF.inicializarPropiedades();

        Huerto.crearHuerto();

        Huerto.plantarSemillaColumna(semillas.get("Primavera").get(0),1);
        Huerto.plantarSemillaColumna(semillas.get("Primavera").get(1),2);
        Huerto.plantarSemillaColumna(semillas.get("Primavera").get(2),3);
        Huerto.plantarSemillaColumna(semillas.get("Primavera").get(3),4);

        Huerto.mostrarHuerto();

        Huerto.atendercCultivos(semillas, g);

        Huerto.mostrarHuerto();
    }
}