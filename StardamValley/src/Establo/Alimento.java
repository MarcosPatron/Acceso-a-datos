package Establo;

public class Alimento {

    private String nombre;
    private double precio;
    private int cantidad;

    public Alimento(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = 0;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public static void mostrarAlimentos(){

    }
}
