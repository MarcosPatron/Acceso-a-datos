package Utils;

import General.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static Utils.General.inicializarDocumentBuilder;

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