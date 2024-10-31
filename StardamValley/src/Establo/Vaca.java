package Establo;

public class Vaca extends Animal {

    private double peso;

    public Vaca(int id, Tipo tipo, String nombre, Alimento alimento, Producto producto, double peso) {
        super(id, tipo, nombre, alimento, producto);
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public void producir(){

    }

}
