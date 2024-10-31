package Utils;

import General.Estaciones;
import General.Granja;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

import static java.lang.Integer.parseInt;


public class PropertiesF {

    /**
     * Nos devuelve el valor del atributo del fichero properties que le
     * hemos pasado por parametros.
     *
     * @param nombre El nombre del elemento en el fichero properties que
     *               queremos seleccionar.
     * @return El valor del fichero properties.
     */
    public static String tomarValor(String nombre, String fichero) {

        String valor;

        try {

            Properties propiedades = new Properties();

            FileInputStream entrada = new FileInputStream(fichero);
            propiedades.load(entrada);

            valor = propiedades.getProperty(nombre);

        } catch (Exception e) {
            throw new RuntimeException("Error al tomar un valor del fichero properties.", e);
        }
        return valor;
    }

    /**
     * Crea un fichero de nombre igual al introducido por paramtros.
     *
     * @param nombreFichero nombre del fichero a crear.
     */
    public static void crearFichero(String nombreFichero) {
        Path path = Paths.get(nombreFichero);

        try {
            Files.createFile(path);

        } catch (Exception e) {
            System.out.println("Error en la creación del fichero.");
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina el fichero de de nombre igual al introducido por paramtros.
     *
     * @param nombreFichero nombre del fichero a eliminar.
     */
    public static void eliminarFichero(String nombreFichero) {

        Path path = Paths.get(nombreFichero);

        try {
            Files.delete(path);

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar un fichero.", e);
        }
    }

    /**
     * Inicializa el fichero personalized_properties con valores por defecto.
     */
    public static void inicializarPropiedades() {

        Properties propiedades = new Properties();

        try {
            FileInputStream entrada = new FileInputStream(Constants.PERSOMNALIZED_PROPERTIES);
            propiedades.load(entrada);
            entrada.close();

            propiedades.setProperty("filas", "4");
            propiedades.setProperty("columnas", "4");
            propiedades.setProperty("presupuesto", "1000");
            propiedades.setProperty("estacion", "Primavera");
            propiedades.setProperty("duracion_estacion", "30");

            FileOutputStream salida = new FileOutputStream(Constants.PERSOMNALIZED_PROPERTIES);
            propiedades.store(salida, "Archivo de configuración presonalizado.");
            salida.close();

        } catch (IOException e) {
            throw new RuntimeException("Error al inicializar el archivo properties de forma predeterminada.", e);
        }
    }

    /**
     * Inicializa el fichero personalized_properties con valores dados.
     */
    public static void setPropiedades() {

        Scanner ent = new Scanner(System.in);
        String filas, columnas, presupuesto, estacion, duracion_estacion;

        System.out.println("Dame el número de filas: ");
        filas = ent.nextLine();
        // Compruebo si el número introducido por el usuario es entero y posistivo
        if (filas == null || !filas.matches("\\d+")) {
            filas = "4";
        }

        System.out.println("Dame el número de columnas: ");
        columnas = ent.nextLine();
        if (columnas == null || !columnas.matches("\\d+")) {
            columnas = "4";
        }

        System.out.println("Dame el presupuesto: ");
        presupuesto = ent.nextLine();
        if (presupuesto == null || !presupuesto.matches("\\d+")) {
            presupuesto = "1000";
        }

        System.out.println("Dame la estación inicial: ");
        estacion = ent.nextLine();

        System.out.println("Dame la duración de la estación: ");
        duracion_estacion = ent.nextLine();
        if (duracion_estacion == null || !duracion_estacion.matches("\\d+")) {
            duracion_estacion = "4";
        }

        Properties propiedades = new Properties();

        try {
            FileInputStream entrada = new FileInputStream(Constants.PERSOMNALIZED_PROPERTIES);
            propiedades.load(entrada);
            entrada.close();

            propiedades.setProperty("filas", filas);
            propiedades.setProperty("columnas", columnas);
            propiedades.setProperty("presupuesto", presupuesto);
            propiedades.setProperty("estacion", estacion);
            propiedades.setProperty("duracion_estacion", duracion_estacion);

            FileOutputStream salida = new FileOutputStream(Constants.PERSOMNALIZED_PROPERTIES);
            propiedades.store(salida, "Archivo de configuración presonalizado.");
            salida.close();

        } catch (IOException e) {
            throw new RuntimeException("Error al inicializar el archivo properties.", e);
        }
    }

    /**
     * Inicializa un nueva granja con los valores del fichero personalized_properties
     *
     * @return La granja ya inicilizada.
     */
    public static Granja nuevaGranja() {

        Properties propiedades = new Properties();
        int presupuesto;
        Estaciones estacion;

        try {
            FileInputStream entrada = new FileInputStream(Constants.PERSOMNALIZED_PROPERTIES);
            propiedades.load(entrada);
            entrada.close();

            presupuesto = parseInt(propiedades.getProperty("presupuesto"));
            estacion = Estaciones.valueOf(propiedades.getProperty("estacion"));

        } catch (IOException e) {
            throw new RuntimeException("Error al cargar una nueva granja.", e);
        }
        return new Granja(presupuesto, estacion);
    }

}
