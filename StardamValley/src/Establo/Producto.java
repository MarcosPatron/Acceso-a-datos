package Establo;

import Utils.DBManagement;

public class Producto {

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
                "\n| Alimentos       | Cantidad Disponible   |" +
                "\n -----------------------------------------");

        for (int i = 1; i <= DBManagement.tamanoTabla("alimentos"); i++) {

            p = DBManagement.cargarProducto(i);

            System.out.printf("| %-15s | %-21d |%n", p.nombre, DBManagement.getCantidadDB("productos", p.nombre));

        }
        System.out.println(" -----------------------------------------");
    }
}
