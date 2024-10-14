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

    public static void escrituraDeportistas() {

        try {
            Document doc = inicializarDocumentBuilder();

            Element cocheNuevo = doc.createElement("coche");
            cocheNuevo.setAttribute("id", "4");

            Element marca = doc.createElement("marca");

            marca.appendChild(doc.createTextNode("Toyota"));

            cocheNuevo.appendChild(marca);

            Element modelo = doc.createElement("modelo");
            modelo.appendChild(doc.createTextNode("Corola"));
            cocheNuevo.appendChild(modelo);

            Element cilindrada = doc.createElement("cilindrada");
            cilindrada.appendChild(doc.createTextNode("1.8"));
            cocheNuevo.appendChild(cilindrada);

            NodeList concesionarioList = doc.getElementsByTagName("concesionario");

            Element concesionario = (Element) concesionarioList.item(0);
            concesionario.appendChild(cocheNuevo);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(General.RUTA_FICHERO));
            transformer.transform(source, result);

            System.out.println("Se ha añadido un coche");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
