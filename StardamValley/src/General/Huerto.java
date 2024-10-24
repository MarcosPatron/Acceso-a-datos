package General;

import Utils.Constantes;
import Utils.PropertiesF;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class Huerto {

    public static void crearHuerto() {

        PropertiesF.eliminarFichero(Constantes.HUERTO);
        PropertiesF.crearFichero(Constantes.HUERTO);

        int filas, columnas;


        try {
            RandomAccessFile raf = new RandomAccessFile(Constantes.HUERTO, "rw");

            filas = parseInt(PropertiesF.tomarValor("filas"));
            columnas = parseInt(PropertiesF.tomarValor("columnas"));

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

        int filas, columnas;

        try{
            RandomAccessFile raf = new RandomAccessFile(Constantes.HUERTO, "r");

            filas = parseInt(PropertiesF.tomarValor("filas"));
            columnas = parseInt(PropertiesF.tomarValor("columnas"));

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    System.out.print("[" + raf.readInt() + " | " + raf.readBoolean() + " | " + raf.readInt() + "]   ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void atendercCultivos(Map<String, ArrayList<Semilla>> semillas, Granja g){

        int filas, columnas;
        String estacion;

        try{
            RandomAccessFile raf = new RandomAccessFile(Constantes.HUERTO, "rw");

            boolean enc = false;
            String idAux;
            int aux;

            estacion = PropertiesF.tomarValor("estacion");
            filas = parseInt(PropertiesF.tomarValor("filas"));
            columnas = parseInt(PropertiesF.tomarValor("columnas"));

            Semilla[] semColumnas = new Semilla[filas];

            //Tomo las semillas que estan plantadas en cada fila
            for (int i = 0; i < filas; i++) {
                idAux = String.valueOf(raf.readInt());
                raf.seek(raf.getFilePointer() + (Integer.BYTES + 1));

                for (int j = 0; j < semillas.get(estacion).size() && !enc; j++) {
                    if(idAux.equals(semillas.get(estacion).get(j).getId())) {
                        semColumnas[i] = semillas.get(estacion).get(j);
                        enc = true;
                    }
                }
                enc = false;
            }
            raf.seek(0);
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    raf.seek((i * columnas + j) * Constantes.TAM_HUERTO_BYTES);
                    raf.seek(raf.getFilePointer() + Integer.BYTES);
                    raf.writeBoolean(true);
                    aux = raf.readInt();
                    if(semColumnas[i] != null && semColumnas[i].getDiasCrecimiento() == aux){
                        raf.seek(raf.getFilePointer() - Constantes.TAM_HUERTO_BYTES); // 0
                        raf.writeInt(-1);
                        raf.writeBoolean(false);
                        raf.writeInt(-1);

                        cultivarHuerto(g, semColumnas[i]);
                    }
                    raf.seek(raf.getFilePointer() + Constantes.TAM_HUERTO_BYTES);
                }
            }


            System.out.println("Se han atendido los cultivos.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void cultivarHuerto(Granja g, Semilla s){

        int cont;

        if(g.getAlmacen().getFrutos().get(s) != null){
           cont =  g.getAlmacen().getFrutos().get(s);
        }
        else{
            cont = 0;
        }

        cont += (int)Math.floor(Math.random() * s.getMaxFrutos() + 1);

        g.getAlmacen().getFrutos().put(s, cont);
    }

    public static void plantarSemillaColumna(Semilla semilla, int col){

        int filas;

        try {
            RandomAccessFile raf = new RandomAccessFile(Constantes.HUERTO, "rw");

            raf.seek((long) Constantes.TAM_HUERTO_BYTES * (col-1));
            if(raf.readInt() != -1) {
                System.out.println("Esta columna ya ha sido plantada, selecciona otra.");
                return;
            }

            filas = parseInt(PropertiesF.tomarValor("filas"));

            raf.seek((long) Constantes.TAM_HUERTO_BYTES * (col-1));

            if(semilla != null){
                for (int i = 0; i < filas; i++) {

                    raf.writeInt(parseInt(semilla.getId()));
                    raf.writeBoolean(false);
                    raf.writeInt(1);

                    raf.seek(raf.getFilePointer() + Constantes.TAM_HUERTO_BYTES * (filas - 1));
                }
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void nuevoDiaHuerto(){

        int filas, columnas;

        try{
            RandomAccessFile raf = new RandomAccessFile(Constantes.HUERTO, "rw");

            filas = parseInt(PropertiesF.tomarValor("filas"));
            columnas = parseInt(PropertiesF.tomarValor("columnas"));
            int aux;

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    raf.seek((i * columnas + j) * Constantes.TAM_HUERTO_BYTES);
                    if(raf.readInt() != -1 && raf.readBoolean()) {
                        raf.seek(raf.getFilePointer() - 1);
                        raf.writeBoolean(false);
                        aux = raf.readInt();
                        raf.seek(raf.getFilePointer() - Integer.BYTES);
                        raf.writeInt(aux + 1);
                    }
                    else{
                        raf.writeBoolean(false);
                    }
                    raf.seek(raf.getFilePointer() + Constantes.TAM_HUERTO_BYTES);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}




























