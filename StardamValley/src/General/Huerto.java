package General;

import Utils.Constantes;
import Utils.PropertiesF;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class Huerto {

    /**
     * Crea un nuevo huerto inicializando un archivo de acceso aleatorio
     * con las dimensiones definidas por las propiedades del sistema.
     *
     * @throws RuntimeException si ocurre alguna excepción al manipular el archivo
     *         o las propiedades no están definidas correctamente.
     */
    public static void crearHuerto() {

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
        } catch (IOException e) {
            throw new RuntimeException("Error al crear el huerto." ,e);
        }
    }

    /**
     * Muestra el huerto ya definido en el archivo de acceso aleatorio.
     *
     * @throws RuntimeException si ocurre alguna excepción al manipular el archivo
     *         o las propiedades no están definidas correctamente.
     */
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

        } catch (IOException e) {
            throw new RuntimeException("Error al mostrar el huerto.", e);
        }
    }

    /**
     * Pone riega el huerto poniendo los valores booleanos introducidos en el RAF a true, y si hay algun fruto que se
     * puede recolectar lo hace, poniendo los valores del huerto como si no estubiese plantado.
     *
     * @throws RuntimeException si ocurre alguna excepción al manipular el archivo
     *         o las propiedades no están definidas correctamente.
     */
    public static void atenderCultivos(Map<String, ArrayList<Semilla>> semillas, Granja g){

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

            Semilla[] semColumnas = new Semilla[columnas];

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
                    raf.seek((i * (columnas) + j) * Constantes.TAM_HUERTO_BYTES);
                    if(raf.readInt() != -1) { //4
                        raf.writeBoolean(true); //5
                    }
                    else{
                        raf.seek(raf.getFilePointer() + 1); //5
                    }
                    aux = raf.readInt(); //9
                    if(semColumnas[j] != null && semColumnas[j].getDiasCrecimiento() == aux){
                        raf.seek(raf.getFilePointer() - Constantes.TAM_HUERTO_BYTES); //OJOOOOOOOOOOOOO
                        raf.writeInt(-1);
                        raf.writeBoolean(false);
                        raf.writeInt(-1);

                        cultivarHuerto(g, semColumnas[j]);
                    }
                    raf.seek(raf.getFilePointer() + Constantes.TAM_HUERTO_BYTES);
                }
            }


            System.out.println("Se han atendido los cultivos.");
        } catch (IOException e) {
            throw new RuntimeException("Error al atender los cultivos.", e);
        }
    }

    /**
     * Cultiva la semilla dada y la introduce en el almacen.
     */
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

    /**
     * Planta las semilla introducida rellenando la columna seleccionada
     *
     * @throws RuntimeException si ocurre alguna excepción al manipular el archivo
     *         o las propiedades no están definidas correctamente.
     */
    public static void plantarSemillaColumna(Semilla semilla, int col){

        int filas, columnas;

        try {
            RandomAccessFile raf = new RandomAccessFile(Constantes.HUERTO, "rw");

            raf.seek((long) Constantes.TAM_HUERTO_BYTES * (col-1));
            if(raf.readInt() != -1) {
                System.out.println("Esta columna ya ha sido plantada, selecciona otra.");
                return;
            }

            filas = parseInt(PropertiesF.tomarValor("filas"));
            columnas = parseInt(PropertiesF.tomarValor("columnas"));

            raf.seek((long) Constantes.TAM_HUERTO_BYTES * (col-1)); // 9

            if(semilla != null){
                for (int i = 0; i < filas; i++) {

                    raf.writeInt(parseInt(semilla.getId())); //13
                    raf.writeBoolean(false); //14
                    raf.writeInt(1); // 18

                    raf.seek(raf.getFilePointer() + Constantes.TAM_HUERTO_BYTES * (columnas - 1)); //
                }
            }
        }catch (IOException e) {
            throw new RuntimeException("Error al plantar las semillas en el huerto.", e);
        }
    }

    /**
     * Pone a false el booleano que indica si el huerto ha sido regado, y si lo ha sido
     * y hay una semilla plantada, aumenta los dias que lleva de crecimiento.
     *
     * @throws RuntimeException si ocurre alguna excepción al manipular el archivo
     *         o las propiedades no están definidas correctamente.
     */
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
        } catch (IOException e) {
            throw new RuntimeException("Error al iniciar un nuevo dia en el huerto.", e);
        }
    }
}




























