package General;


import Establo.Producto;
import Huerto.Semilla;
import Utils.DBManagement;

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
        DBManagement.anadirTransaccion("VENTA", "HUERTO", cont);
        System.out.println("Se ha vendido la cosecha.");
        return cont;
    }

    /**
     * Vende los productos almacenados
     *
     * @return El dinero que se ha adquirido al vender los productos
     */
    public int venderProductos(){

        double cont = 0, total = 0;

        System.out.println("Vendiendo productos...");

        for (int i = 1; i <= DBManagement.tamanoTabla("productos"); i++) {

            Producto p = DBManagement.cargarProducto(i);

            cont += DBManagement.getCantidadDB("productos", p.getNombre()) *
            p.getPrecio();

            if (cont != 0){
                System.out.println("- Se ha vendido " +
                        DBManagement.getCantidadDB("productos", p.getNombre()) +
                        " unidades de " + p.getNombre() + " por " + cont + "€.");
            }

            DBManagement.setCantidadDB("productos", 0, p.getNombre());

            total += cont;
            cont = 0;
        }

        if(total == 0){
            System.out.println("No hay nada en el almacen para vender.");
        }
        else{
            DBManagement.anadirTransaccion("VENTA", "PRODUCTO", total);
            System.out.println("TOTAL INGRESOS: " + total + "€.");
        }
        return (int) total;
    }
}
