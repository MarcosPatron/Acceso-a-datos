package General;


import java.util.HashMap;
import java.util.Map;

public class Almacen {

    private static HashMap<String, Integer> frutos;

    public Almacen(HashMap<String, Integer> frutas) {
        this.frutos = frutas;
    }

    public HashMap<String, Integer> getFrutos() {
        return frutos;
    }

    public void setFrutos(HashMap<String, Integer> frutos) {
        this.frutos = frutos;
    }

    public static void mostrarAlmacen(){

        for (Map.Entry<String, Integer> frutos : frutos.entrySet()) {
            System.out.print("[" + frutos.getKey()+ " - " + frutos.getValue() + "]");
        }
        System.out.println();
    }
}
