package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

public class PropertiesF {

    public static void crearFichero(String nombreFichero) {
        Path path = Paths.get(nombreFichero);

        try {
            Files.createFile(path);

        } catch (Exception e) {
            System.out.println("Error en la creación del fichero.");
            throw new RuntimeException(e);
        }
    }

    public static void eliminarFichero(String nombreFichero) {

        Path path = Paths.get(nombreFichero);

        try {
            Files.delete(path);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void inicializarPropiedades(){

        Properties propiedades = new Properties();

        try {
            FileInputStream entrada = new FileInputStream(Constantes.PERSOMNALIZED_PROPERTIES);
            propiedades.load(entrada);
            entrada.close();

            propiedades.setProperty("filas", "4");
            propiedades.setProperty("columnas", "4");
            propiedades.setProperty("presupuesto", "1000");
            propiedades.setProperty("estacion", "PRIMAVERA");
            propiedades.setProperty("duracion_esacion", "30");

            FileOutputStream salida = new FileOutputStream(Constantes.PERSOMNALIZED_PROPERTIES);
            propiedades.store(salida, "Archivo de configuración presonalizado.");
            salida.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setPropiedades(){

        Scanner ent = new Scanner(System.in);
        String filas = "4", columnas = "4", presupuesto = "1000", estacion = "PRIMAVERA", duracion_estacion = "30";

        System.out.println("Dame el número de filas: ");
        filas = ent.nextLine();

        System.out.println("Dame el número de columnas: ");
        columnas = ent.nextLine();

        System.out.println("Dame el presupuesto: ");
        presupuesto = ent.nextLine();

        System.out.println("Dame la estaición inicial: ");
        estacion =  ent.nextLine();

        System.out.println("Dame la duración de la estación: ");
        duracion_estacion =  ent.nextLine();

        Properties propiedades = new Properties();

        try {
            FileInputStream entrada = new FileInputStream(Constantes.PERSOMNALIZED_PROPERTIES);
            propiedades.load(entrada);
            entrada.close();

            propiedades.setProperty("filas", filas);
            propiedades.setProperty("columnas", columnas);
            propiedades.setProperty("presupuesto", presupuesto);
            propiedades.setProperty("estacion", estacion);
            propiedades.setProperty("duracion_esacion", duracion_estacion);

            FileOutputStream salida = new FileOutputStream(Constantes.PERSOMNALIZED_PROPERTIES);
            propiedades.store(salida, "Archivo de configuración presonalizado.");
            salida.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
