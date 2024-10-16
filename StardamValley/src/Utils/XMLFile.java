package Utils;

import General.Semillas;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class XMLFile {


    public static void cargarSemillas(Map<String, ArrayList<Semillas>> semillas){

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(Constantes.SEMILLAS));

            // Normalizamos el archivo XML
            doc.getDocumentElement().normalize();

            // Obtenemos todos los elementos "semilla" (elemento complejo)
            NodeList sem= doc.getElementsByTagName("semilla");


            // Procesamos cada uno de los semilla
            for (int i = 0; i < sem.getLength(); i++) {
                // Recuperamos el nodo en la posicion i
                Node nodoSemilla = sem.item(i);
                nodoSemilla.getAttributes();

                // Nos aseguramos que el nodo es un elemento (y no es, por ejemplo, un comentario)
                if (nodoSemilla.getNodeType() == Node.ELEMENT_NODE) {
                    // Convertimos el nodo a Element para poder procesarlo
                    Element coche = (Element) nodoSemilla;

                    // Recuperamos los elementos simples
                    String marca = coche.getElementsByTagName("marca").item(0).getTextContent();
                    String modelo = coche.getElementsByTagName("modelo").item(0).getTextContent();
                    double cilindrada = Double.parseDouble(coche.getElementsByTagName("cilindrada").item(0).getTextContent());

                    String id = coche.getAttribute("id");;
                    String nombre;
                    String estacion;
                    int diasCrecimiento;
                    int precioCompra;
                    int precioVenta;
                    int maxFrutos;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        ArrayList<Semillas> primavera = new ArrayList<>();
        ArrayList<Semillas> verano = new ArrayList<>();
        ArrayList<Semillas> otono = new ArrayList<>();
        ArrayList<Semillas> invierno = new ArrayList<>();


        semillas.put("primavera", primavera);
        semillas.put("verano", verano);
        semillas.put("otono", otono);
        semillas.put("invierno", invierno);
    }
}
