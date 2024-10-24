package General;

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
    public static String eleccion(){

        Scanner ent = new Scanner(System.in);
        String sal;

        try{
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
    public static void menuInicio(){

        Path path = Paths.get(Constantes.STARDAM_VALLEY);

        System.out.println("BINEVENIDO A STARDAM VALLEY" +
                "\n-----------------------------------" +
                "\n1. NUEVA PARTIDA");

        if(Files.exists(path)){
            System.out.println("2. CARGAR PARTIDA");
        }
    }

    /**
     * Muestra el manu de las funcionalidades del juego.
     */
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

    /**
     * Inicializa la granja con la características de nuestra nueva partida.
     *
     * @return La granja ya inicializada
     */
    public static Granja nuevaPartida(){

        String resp;

        PropertiesF.eliminarFichero(Constantes.STARDAM_VALLEY);

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
        }while(!salir);

        salir = false;

        do{
            menuFuncionalidades();
            switch (eleccion()) {
                case "1":
                    g.nuevoDia(semillas);
                    Huerto.mostrarHuerto();
                    break;
                case "2":
                    Huerto.atenderCultivos(semillas, g);
                    Huerto.mostrarHuerto();
                    break;
                case "3":
                    Huerto.mostrarHuerto();
                    System.out.println("¿En que posición quires plantar las semillas?");
                    String pos = eleccion();
                    g.getSemillasVenta().mostrarTienda();
                    System.out.println("¿Qué semilla quieres plantar?");
                    Huerto.plantarSemillaColumna(g.getSemillasVenta().comprarSemillas(g, eleccion()), parseInt(pos));
                    Huerto.mostrarHuerto();
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
        }while(!salir);
        BinF.guardarPartida(g);
    }
}