package Utils;

import General.Granja;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BinF {

    /**
     * Carga la granja y la inicializa en el programa.
     *
     * @return La granja guardada en la ultima sesión.
     */
    public static Granja cargarGranja(){

        Path path = Paths.get(Constants.STARDAM_VALLEY);
        Granja g;

        try{
            InputStream fis = Files.newInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);

            g = (Granja) ois.readObject();

        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la granja guardada en el programa.", e);
        }
        return g;
    }

    /**
     * Guarda en el fichero la granja introducida por parametros.
     *
     * @param g Nuestra granja.
     */
    public static void guardarPartida(Granja g){

        Path path = Paths.get(Constants.STARDAM_VALLEY);

        try{
            OutputStream fos = Files.newOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(g);

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la partida.", e);
        }
    }


}
