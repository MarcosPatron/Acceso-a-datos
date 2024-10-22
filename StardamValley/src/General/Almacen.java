package General;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Almacen implements Serializable {

    private HashMap<Semilla, Integer> frutos;

    public Almacen() {
        this.frutos = new HashMap<>();
    }

    public Almacen(HashMap<Semilla, Integer> frutos) {
        this.frutos = frutos;
    }

    public HashMap<Semilla, Integer> getFrutos() {
        return frutos;
    }

    public void setFrutos(HashMap<Semilla, Integer> frutos) {
        this.frutos = frutos;
    }

    public void mostrarAlmacen(){

        for (Map.Entry<Semilla, Integer> frutos : this.frutos.entrySet()) {
            System.out.print("[" + frutos.getKey().getNombre()+ " - " + frutos.getValue() + "]");
        }
        System.out.println();
    }

    public int venderCosecha(){
        int cont = 0;

        for (Map.Entry<Semilla, Integer> frutos : this.frutos.entrySet()) {
            cont += frutos.getKey().getPrecioVenta() * frutos.getValue();
        }

        return cont;
    }
}
