package Establo;

import General.Granja;
import Utils.DBManagement;

import java.io.Serializable;

public class Vaca extends Animal implements Serializable {

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
    public void producir(Granja g){

        int cant = (int) peso / 100;

        DBManagement.setCantidadDB("productos",
                DBManagement.getCantidadDB("productos",
                        getProducto().getNombre()) + cant, getProducto().getNombre());
        DBManagement.tablaHistorial("produccion", getId(), cant);
    }

    public void mosrtarAnimal(){
        String alimentado;
        if(isAlimentado()){
            alimentado = "Si";
        }
        else{
            alimentado = "No";
        }
        System.out.println("| " + getId() + "         | " + getTipo() + "      | " + alimentado + "            | "
        + getAlimento().getNombre() + "      | " + getProducto().getNombre() + "     |");
    }
}
