package Establo;

import General.Granja;
import Utils.DBManagement;

import java.io.Serializable;
import java.util.ArrayList;

public class Producto implements Serializable {

    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public static void mostrarProductos(){

        Producto p;

        System.out.println(" -----------------------------------------" +
                "\n| Productos       | Cantidad Disponible   |" +
                "\n -----------------------------------------");

        for (int i = 1; i <= DBManagement.tamanoTabla("productos"); i++) {

            p = DBManagement.cargarProducto(i);

            System.out.printf("| %-15s | %-21d |%n", p.nombre, DBManagement.getCantidadDB("productos", p.nombre));

        }
        System.out.println(" -----------------------------------------");
    }

    public static void recogerProduccion(Granja g, ArrayList<Animal> animales){

        System.out.println("Comenzando producción...");
        for(Animal a : animales){

            if(a.isAlimentado()){
                a.producir(g);
            }
        }
    }
}
