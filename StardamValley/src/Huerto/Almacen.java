package Huerto;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
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

    /**
     * Muestra los frutos almacenados en el almacen.
     *
     */
    public void mostrarAlmacen(){

        for (Map.Entry<Semilla, Integer> frutos : this.frutos.entrySet()) {
            System.out.print("[" + frutos.getKey().getNombre()+ " - " + frutos.getValue() + "]");
        }
        System.out.println();
    }

    /**
     * Vende los frutos almacenados en la variable frutos
     *
     * @return El dinero que se ha adquirido al vender los frutos
     */
    public int venderCosecha(){

        int cont = 0;
        Iterator<Map.Entry<Semilla, Integer>> iter = this.frutos.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry<Semilla, Integer> entry = iter.next();

            cont += entry.getKey().getPrecioVenta() * entry.getValue();

            iter.remove();
        }

        if(cont == 0){
            System.out.println("No hay nada en el almacen para vender.");
        }
        System.out.println("Se ha vendido la cosecha.");
        return cont;
    }
}
