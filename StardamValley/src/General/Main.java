package General;

import Huerto.Huerto;
import Utils.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Integer.parseInt;


public class Main {


    /**
     * Pide al usuario un valor por pantalla
     *
     * @return La cadena de texto que ha introducido el usuario.
     */
    public static String eleccion() {

        Scanner ent = new Scanner(System.in);
        String sal;

        try {
            sal = ent.nextLine();
        } catch (InputMismatchException e) {
            sal = "";
            throw new RuntimeException("Error al introducir valores por pantalla.", e);
        }
        return sal;
    }

    /**
     * Muestra el menu de inicio del juego.
     */
    public static void menuInicio(Granja g, Map<String, ArrayList<Semilla>> semillas) {

        boolean salir = false;

        Path path = Paths.get(Constants.STARDAM_VALLEY);
        do {
            System.out.println("BINEVENIDO A STARDAM VALLEY" +
                    "\n-----------------------------------" +
                    "\n1. NUEVA PARTIDA");

            if (Files.exists(path)) {
                System.out.println("2. CARGAR PARTIDA");
            }
            menuInicio(g, semillas);
            switch (eleccion()) {
                case "1":
                    g = nuevaPartida();
                    g.nuevoDia(semillas);
                    salir = true;
                    break;
                case "2":
                    g = BinF.cargarGranja();
                    salir = true;
                    break;
                default:
                    System.out.println("ERROR. Esa elección no existe.");
            }
        } while (!salir);
    }

    public static void menuStardam(Granja g, Map<String, ArrayList<Semilla>> semillas) {

        boolean salir = false;

        System.out.println("    STARDAM VALLEY" +
                "\n1. INICIAR NUEVO DIA" +
                "\n2. HUERTO" +
                "\n3. ESTABLOS" +
                "\n4. SALIR");

        switch (eleccion()) {
            case "1":
                //Nuevo dia
                break;
            case "2":
                menuHuerto(g, semillas);
                break;
            case "3":
                menuEstablos(g, semillas);
                break;
            case "4":
                salir = true;
                break;
        }
    }

    public static void menuEstablos(Granja g, Map<String, ArrayList<Semilla>> semillas) {

        boolean salir = false;

        do {
            System.out.println("    ESTABLOS" +
                    "\n---------------------------------------" +
                    "\n1. PRODUCIR" +
                    "\n2. ALIMENTAR" +
                    "\n3. VENDER PRODUCTOS" +
                    "\n4. RELLENAR COMEDERO" +
                    "\n5. MOSTRAR ANIMALES" +
                    "\n6. VOLVER");

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
                    salir = true;
                    break;
            }

        } while (!salir);


    }

    /**
     * Muestra el manu de las funcionalidades del juego.
     */
    public static void menuHuerto(Granja g, Map<String, ArrayList<Semilla>> semillas) {

        boolean salir = false;

        do {
            System.out.println("    HUERTO" +
                    "\n---------------------------------------" +
                    "\n1. ATENDER CULTIVOS" +
                    "\n2. PLANTAR CULTIVOS EN COLUMNA" +
                    "\n3. VENDER COSECHA" +
                    "\n4. MOSTRAR INFORMACION DE LA GRANJA" +
                    "\n5. VOLVER");
            switch (eleccion()) {
                /*
                case "1":
                    g.nuevoDia(semillas);
                    Huerto.mostrarHuerto();
                    break;

                 */
                case "1":
                    Huerto.atenderCultivos(semillas, g);
                    Huerto.mostrarHuerto();
                    break;
                case "2":
                    Huerto.mostrarHuerto();
                    System.out.println("¿En que posición quires plantar las semillas?");
                    String pos = eleccion();
                    g.getSemillasVenta().mostrarTienda();
                    System.out.println("¿Qué semilla quieres plantar?");
                    Huerto.plantarSemillaColumna(g.getSemillasVenta().comprarSemillas(g, eleccion()), parseInt(pos));
                    Huerto.mostrarHuerto();
                    break;
                case "3":
                    g.setDinero(g.getDinero() + g.getAlmacen().venderCosecha());
                    break;
                case "4":
                    g.mostrarGranja();
                    break;
                case "5":
                    salir = true;
                    break;
                default:
                    System.out.println("ERROR. Esa elección no existe.");
            }
        } while (!salir);

    }

    /**
     * Inicializa la granja con la características de nuestra nueva partida.
     *
     * @return La granja ya inicializada
     */
    public static Granja nuevaPartida() {

        String resp;

        PropertiesF.eliminarFichero(Constants.STARDAM_VALLEY);

        PropertiesF.crearFichero(Constants.STARDAM_VALLEY);

        System.out.println("¿Quieres cambiar la configaración de tu partida?");
        resp = eleccion();

        if (resp.equalsIgnoreCase("si")) {
            PropertiesF.setPropiedades();
        } else {
            PropertiesF.inicializarPropiedades();
        }

        Huerto.crearHuerto();
        return PropertiesF.nuevaGranja();
    }

    public static void main(String[] args) {

        Map<String, ArrayList<Semilla>> semillas = new HashMap<>();

        XMLFile.cargarSemillas(semillas);
        Granja g = null;

        BinF.guardarPartida(g);
    }
}