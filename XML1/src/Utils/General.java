package Utils;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class General {

    public final static String RUTA_DIRECTORIO = "resources" + File.separator;
    public final static String RUTA_FICHERO = RUTA_DIRECTORIO + "datos.xml";

    public static void crearFichero() {
        Path path = Paths.get(RUTA_DIRECTORIO);
        Path pathFichero = Paths.get(RUTA_FICHERO);
        try {
            if (Files.exists(path)) {
                if (Files.exists(pathFichero)) {
                    return;
                } else {
                    Files.createFile(pathFichero);
                }
            } else {
                Files.createDirectory(path);
                Files.createFile(pathFichero);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Document inicializarDocumentBuilder() throws Exception {
        // Declaramos las clases necesarias para DOM
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File(RUTA_FICHERO));

        return doc;
    }


}
