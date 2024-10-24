package Utils;

import General.Semilla;
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


    public static void cargarSemillas(Map<String, ArrayList<Semilla>> semillas){

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(Constantes.SEMILLAS));

            ArrayList<Semilla> primavera = new ArrayList<>();
            ArrayList<Semilla> verano = new ArrayList<>();
            ArrayList<Semilla> otono = new ArrayList<>();
            ArrayList<Semilla> invierno = new ArrayList<>();

            doc.getDocumentElement().normalize();

            NodeList sem= doc.getElementsByTagName("semilla");

            for (int i = 0; i < sem.getLength(); i++) {
                Node nodoSemilla = sem.item(i);
                nodoSemilla.getAttributes();

                if (nodoSemilla.getNodeType() == Node.ELEMENT_NODE) {
                    Element semilla = (Element) nodoSemilla;

                    String id = semilla.getAttribute("id");;
                    String nombre = semilla.getElementsByTagName("nombre").item(0).getTextContent();
                    String[] estacion = (semilla.getElementsByTagName("estacion").item(0).getTextContent()).split("-") ;

                    int diasCrecimiento = Integer.parseInt(semilla.getElementsByTagName("diasCrecimiento").item(0).getTextContent());
                    int precioCompra= Integer.parseInt(semilla.getElementsByTagName("precioCompraSemilla").item(0).getTextContent());
                    int precioVenta = Integer.parseInt(semilla.getElementsByTagName("precioVentaFruto").item(0).getTextContent());
                    int maxFrutos = Integer.parseInt(semilla.getElementsByTagName("maxFrutos").item(0).getTextContent());

                    for (int j = 0; j < estacion.length; j++) {

                        String aux = estacion[j];

                        switch (aux) {
                            case "Primavera":
                                primavera.add(new Semilla(id, nombre, estacion[j], diasCrecimiento, precioCompra, precioVenta, maxFrutos));
                                break;
                            case "Verano":
                                verano.add(new Semilla(id, nombre, estacion[j], diasCrecimiento, precioCompra, precioVenta, maxFrutos));
                                break;
                            case "Otoño":
                                otono.add(new Semilla(id, nombre, estacion[j], diasCrecimiento, precioCompra, precioVenta, maxFrutos));
                                break;
                            case "Invierno":
                                invierno.add(new Semilla(id, nombre, estacion[j], diasCrecimiento, precioCompra, precioVenta, maxFrutos));
                                break;
                            default:
                                System.out.println("Semilla ");
                        }
                    }
                }
            }

            semillas.put("Primavera", primavera);
            semillas.put("Verano", verano);
            semillas.put("Otoño", otono);
            semillas.put("Invierno", invierno);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
