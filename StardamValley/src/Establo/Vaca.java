package Establo;

import General.Granja;
import Utils.DBManagement;

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
    public void producir(Granja g){

        int cant = (int) peso / 100;
        if(g.getAlmacen().getProductos().get(getProducto()) != null){
            cant += g.getAlmacen().getProductos().get(getProducto());
        }

        g.getAlmacen().getProductos().put(getProducto(), cant);

        DBManagement.setCantidadDB("productos",
                DBManagement.getCantidadDB("productos",
                        getAlimento().getNombre()) + cant, getAlimento().getNombre());
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
