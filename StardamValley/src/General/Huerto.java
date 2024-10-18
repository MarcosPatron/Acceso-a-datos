package General;

import Utils.Constantes;
import Utils.PropertiesF;

import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.Properties;

public class Huerto {

    public static void crearHuerto() {

        PropertiesF.eliminarFichero(Constantes.HUERTO);
        PropertiesF.crearFichero(Constantes.HUERTO);

        try {
            RandomAccessFile raf = new RandomAccessFile(Constantes.HUERTO, "rw");

            Properties propiedades = new Properties();

            FileInputStream entrada = new FileInputStream(Constantes.HUERTO);
            propiedades.load(entrada);

            int filas = Integer.parseInt(propiedades.getProperty("filas"));
            int columnas = Integer.parseInt(propiedades.getProperty("columnas"));

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    raf.writeInt(-1);
                    raf.writeBoolean(false);
                    raf.writeInt(-1);

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarHuerto() {

        try{

            RandomAccessFile raf = new RandomAccessFile(Constantes.HUERTO, "r");

            Properties propiedades = new Properties();

            FileInputStream entrada = new FileInputStream(Constantes.HUERTO);
            propiedades.load(entrada);

            int filas = Integer.parseInt(propiedades.getProperty("filas"));
            int columnas = Integer.parseInt(propiedades.getProperty("columnas"));

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    System.out.println("[" + raf.readInt() + "|" + raf.readBoolean() + "|" + raf.readInt() + "]");

                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
