package Utils;

import General.Estaciones;
import General.Granja;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class PropertiesF {

    public static String tomarValor(String nombre){

        String valor;

        try{
            RandomAccessFile raf = new RandomAccessFile(Constantes.HUERTO, "rw");

            Properties propiedades = new Properties();

            FileInputStream entrada = new FileInputStream(Constantes.PERSOMNALIZED_PROPERTIES);
            propiedades.load(entrada);

            valor = propiedades.getProperty(nombre);

        } catch (Exception e) {
            throw new RuntimeException("Error al tomar un valor del archivo properties.", e);
        }
        return valor;
    }

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
            throw new RuntimeException("Error al crear un fichero.", e);
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
            propiedades.setProperty("estacion", "Primavera");
            propiedades.setProperty("duracion_esacion", "30");

            FileOutputStream salida = new FileOutputStream(Constantes.PERSOMNALIZED_PROPERTIES);
            propiedades.store(salida, "Archivo de configuración presonalizado.");
            salida.close();

        } catch (IOException e) {
            throw new RuntimeException("Error al inicializar el archivo properties de forma predeterminada.", e);
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

        } catch (IOException e) {
            throw new RuntimeException("Error al inicializar el archivo properties.", e);
        }
    }

    public static Granja nuevaGranja() {

        Properties propiedades = new Properties();
        int presupuesto;
        Estaciones estacion;

        try {
            FileInputStream entrada = new FileInputStream(Constantes.PERSOMNALIZED_PROPERTIES);
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
