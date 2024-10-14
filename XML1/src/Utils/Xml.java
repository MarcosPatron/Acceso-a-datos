package Utils;

import General.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static Utils.General.inicializarDocumentBuilder;

public class Xml {

    public static void migrarDatos(ArrayList<Deportista> deportistas) {

        //Paso los datos del fichero binario a un ArrayList
        Path path = Paths.get(General.RUTA_FICHERO);

        deportistas = Lectura.leer();

        try{
            Document doc = inicializarDocumentBuilder();


            Element root = doc.createElement("deportistas");
            doc.appendChild(root);

            for(int i = 0; i < deportistas.size(); i++){
                Element deportista = doc.createElement("deportista");
                deportista.setAttribute("id", String.valueOf(i+1));
                root.appendChild(deportista);

                Element dni = doc.createElement("dni");
                dni.appendChild(doc.createTextNode(deportistas.get(i).getDni()));
                deportista.appendChild(dni);

                Element nombre = doc.createElement("nombre");
                dni.appendChild(doc.createTextNode(deportistas.get(i).getDni()));
                deportista.appendChild(nombre);

                Element fechaNac = doc.createElement("fechaNac");
                dni.appendChild(doc.createTextNode(deportistas.get(i).getDni()));
                deportista.appendChild(fechaNac);

                Element deporte = doc.createElement("deporte");
                dni.appendChild(doc.createTextNode(deportistas.get(i).getDni()));
                deportista.appendChild(deporte);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(General.RUTA_FICHERO));

            transformer.transform(source, result);
        } catch (Exception e) {
            System.out.println("No se ha podido escribir en el fichero.");
            throw new RuntimeException(e);
        }
    }
}
