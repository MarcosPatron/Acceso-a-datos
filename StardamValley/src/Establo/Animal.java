package Establo;

public abstract class Animal {

    private int id;
    private Tipo tipo;
    private String nombre;
    private int dia_insercion;
    private Alimento alimento;
    private Producto producto;
    //private double peso


    public Animal(int id, Tipo tipo, String nombre, Alimento alimento, Producto producto) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.dia_insercion = 0;
        this.alimento = alimento;
        this.producto = producto;
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


    public abstract void producir();

}
