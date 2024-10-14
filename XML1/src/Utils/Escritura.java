package Utils;

import General.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

    public static void lecturaDeportistas() {
        try {
            Document doc = General.inicializarDocumentBuilder();

            // Normalizamos el archivo XML
            doc.getDocumentElement().normalize();

            System.out.println("El elemento raíz es: " + doc.getDocumentElement().getTagName());

            NodeList listaCoches = doc.getElementsByTagName("coche");
            System.out.println("Número de coches: " + listaCoches.getLength());

            for (int i = 0; i < listaCoches.getLength(); i++) {

                Node nodoCoche = listaCoches.item(i);
                nodoCoche.getAttributes();

                if (nodoCoche.getNodeType() == Node.ELEMENT_NODE) {

                    Element coche = (Element) nodoCoche;

                    String id = coche.getAttribute("id");

                    String marca = coche.getElementsByTagName("marca").item(0).getTextContent();
                    String modelo = coche.getElementsByTagName("modelo").item(0).getTextContent();
                    double cilindrada = Double.parseDouble(coche.getElementsByTagName("cilindrada").item(0).getTextContent());

                    System.out.println("Coche " + id + ". Marca: " + marca + ". Modelo: " + modelo + ". Clindrada: " + cilindrada);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
