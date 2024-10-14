package Utils;

import General.*;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Escritura {


    public static void escribir(ArrayList<Deportista> deportistas) {

        Path path = Paths.get(General.RUTA_FICHERO);

        try{

            OutputStream os = Files.newOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(deportistas);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
