package Utils;

import General.Granja;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BinF {

    public static Granja cargarGranja(){

        Path path = Paths.get(Constantes.STARDAM_VALLEY);
        Granja g;
        try{

            InputStream fis = Files.newInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);

            g = (Granja) ois.readObject();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return g;
    }

    public static void guardarPartida(Granja g){

        Path path = Paths.get(Constantes.STARDAM_VALLEY);

        try{

            OutputStream fos = Files.newOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(g);

            oos.writeObject(g);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
