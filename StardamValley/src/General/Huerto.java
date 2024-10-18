package General;

import Utils.Constantes;
import Utils.PropertiesF;

import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

public class Huerto {

    private static int filas;
    private static int columnas;

    public static void crearHuerto() {

        PropertiesF.eliminarFichero(Constantes.HUERTO);
        PropertiesF.crearFichero(Constantes.HUERTO);

        try {
            RandomAccessFile raf = new RandomAccessFile(Constantes.HUERTO, "rw");

            Properties propiedades = new Properties();

            FileInputStream entrada = new FileInputStream(Constantes.PERSOMNALIZED_PROPERTIES);
            propiedades.load(entrada);

            filas = Integer.parseInt(propiedades.getProperty("filas"));
            columnas = Integer.parseInt(propiedades.getProperty("columnas"));

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    raf.writeInt(-1);
                    raf.writeBoolean(false);
                    raf.writeInt(-1);

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarHuerto() {

        try{

            RandomAccessFile raf = new RandomAccessFile(Constantes.HUERTO, "r");

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    System.out.print("[" + raf.readInt() + " | " + raf.readBoolean() + " | " + raf.readInt() + "] ");

                }
                System.out.println();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void atendercCultivos(Map<String, ArrayList<Semilla>> semillas){

        try{

            RandomAccessFile raf = new RandomAccessFile(Constantes.HUERTO, "rw");

            Properties propiedades = new Properties();

            FileInputStream entrada = new FileInputStream(Constantes.PERSOMNALIZED_PROPERTIES);
            propiedades.load(entrada);

            Semilla aux;
            int auxInt;
            boolean enc = false;
            String idAux;
            String estacion = propiedades.getProperty("estacion");


            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {

                    idAux = String.valueOf(raf.readInt());
                    raf.writeBoolean(true);

                    for (int k = 0; k < semillas.get(estacion).size() && !enc; k++) {
                        if(idAux.equals(semillas.get(estacion).get(k).getId())){
                            aux = semillas.get(estacion).get(k);
                            if(aux.getDiasCrecimiento() >= raf.readInt()){

                                raf.seek(raf.getFilePointer()-Constantes.TAM_HUERTO_BYTES);
                                raf.writeInt(-1);
                                raf.writeBoolean(false);
                                raf.writeInt(-1);

                            }
                            else{
                                raf.seek(raf.getFilePointer()-Integer.BYTES);
                                auxInt = raf.readInt();
                                raf.seek(raf.getFilePointer()-Integer.BYTES);
                                raf.writeInt(auxInt+1);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}




























