package Establo;

import General.Estaciones;
import General.Granja;
import Utils.DBManagement;

import java.io.Serializable;
import java.util.Random;

public class Cerdo extends Animal implements Serializable {

    public Cerdo(int id, Tipo tipo, String nombre, Alimento alimento, Producto producto) {
        super(id, tipo, nombre, alimento, producto);
    }

    @Override
    public void producir(Granja g){

        int cant;
        Random rand = new Random();


        switch (g.getEstacion()){
            case Estaciones.Primavera:
                cant = rand.nextInt(2) + 2;
                break;
            case Estaciones.Verano:
                cant = rand.nextInt(2)  + 2;
                break;
            case Estaciones.Otono:
                cant = rand.nextInt(2);
                break;
            case Estaciones.Invierno:
                cant = 0;
                System.out.println(getNombre() + " no produce nada en invierno.");
                break;
            default: cant = 0;
        }

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
