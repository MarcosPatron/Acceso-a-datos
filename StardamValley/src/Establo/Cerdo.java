package Establo;

import General.Estaciones;
import General.Granja;
import Utils.DBManagement;

public class Cerdo extends Animal {

    public Cerdo(int id, Tipo tipo, String nombre, Alimento alimento, Producto producto) {
        super(id, tipo, nombre, alimento, producto);
    }

    @Override
    public void producir(Granja g){

        int cant;

        switch (g.getEstacion()){
            case Estaciones.Primavera:
                cant = (int) Math.floor(Math.random() * 1 + 2);
                break;
            case Estaciones.Verano:
                cant = (int) Math.floor(Math.random() * 1 + 2);
                break;
            case Estaciones.Otono:
                cant = (int) Math.floor(Math.random() * 1);
                break;
            case Estaciones.Invierno:
                cant = 0;
                break;
            default: cant = 0;
        }

        if(g.getAlmacen().getProductos().get(getProducto()) != null){
            cant += g.getAlmacen().getProductos().get(getProducto());
        }

        g.getAlmacen().getProductos().put(getProducto(), cant);

        DBManagement.setCantidadDB("productos",
                DBManagement.getCantidadDB("productos",
                        getProducto().getNombre()) + cant, getProducto().getNombre());
        DBManagement.tablaHistorial("produccion", getId(), cant);
    }

    @Override
    public void mosrtarAnimal(){
        String alimentado;
        if(isAlimentado()){
            alimentado = "Si";
        }
        else{
            alimentado = "No";
        }
        System.out.println("| " + getId() + "        | " + getTipo() + "     | " + alimentado + "            | "
                + getAlimento().getNombre() + "      | " + getProducto().getNombre() + "     |");
    }
}
