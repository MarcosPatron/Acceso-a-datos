package Utils;

import General.Estaciones;

import java.io.FileWriter;
import java.util.Properties;

public class PropertiesF {

    private static final String DEFAULT_PROPERTIES = "resources/default_config.properties";
    private static final String PERSOMNALIZED_PROPERTIES = "resources/personalized_config.properties";

    private int filas;
    private int col;
    private int presupuesto;
    private Estaciones estacion;
    private int duracion_estacion;

    public static void inicializar() {
        Properties p = new Properties();

        p.setProperty("filas", "4");
        p.setProperty("columnas", "4");
        p.setProperty("propuesto", "1000");
        p.setProperty("estacion", "PRIMAVERA");
        p.setProperty("duracion_esacion", "30");
        
        try{
            p.store(new FileWriter(PERSOMNALIZED_PROPERTIES), "Fichero de configuracion personalizado.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void leer(){


    }

    public static void escribir(){

    }

}
