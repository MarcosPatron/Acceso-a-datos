package Establo;

import General.Granja;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Animal implements Serializable {

    private int id;
    private Tipo tipo;
    private String nombre;
    private int dia_insercion;
    private Alimento alimento;
    private Producto producto;
    private boolean producido;
    private boolean alimentado;


    public Animal(int id, Tipo tipo, String nombre, Alimento alimento, Producto producto) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.dia_insercion = 0;
        this.alimento = alimento;
        this.producto = producto;
        this.producido = false;
        alimentado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDia_insercion() {
        return dia_insercion;
    }

    public void setDia_insercion(int dia_insercion) {
        this.dia_insercion = dia_insercion;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public boolean isProducido() {
        return producido;
    }

    public void setProducido(boolean producido) {
        this.producido = producido;
    }

    public boolean isAlimentado() {
        return alimentado;
    }

    public void setAlimentado(boolean alimentado) {
        this.alimentado = alimentado;
    }

    public abstract void producir(Granja g);


    public abstract void mosrtarAnimal();

    /**
     * Muestra los animales en una tabla
     */
    public static void mostrarEstablo(ArrayList<Animal> animales){
        System.out.println(" --------------------------------------------------------------- ");
        System.out.println("| ID        | Tipo      | Alimentado    | Alimento  | Producto  |"
        + "\n --------------------------------------------------------------- ");
        for(Animal a : animales){
            a.mosrtarAnimal();
        }
        System.out.println(" --------------------------------------------------------------- ");
    }

    /**
     * Inicia un nuevo día para lso animales.
     */
    public static void nuevoDiaAnimal(ArrayList<Animal> animales){
        for(Animal a : animales){
            a.setDia_insercion(a.dia_insercion++);
            a.setAlimentado(false);
            a.setProducido(false);
        }
    }
}
