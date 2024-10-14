package Utils;

import General.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Lectura {

    public static ArrayList<Deportista> leer(){

        ArrayList<Deportista> d;
        Path path = Paths.get(General.RUTA_FICHERO);

        try{

            InputStream is = Files.newInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(is);
            d = (ArrayList<Deportista>) ois .readObject();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return d;
    }

}
