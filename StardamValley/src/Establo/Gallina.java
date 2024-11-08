package Establo;

import General.Granja;
import Utils.DBManagement;

import java.io.Serializable;

public class Gallina extends Animal implements Serializable {

    public Gallina(int id, Tipo tipo, String nombre, Alimento alimento, Producto producto) {
        super(id, tipo, nombre, alimento, producto);
    }

    @Override
    public void producir(Granja g){

        int cant;
        int edad = g.getDiaJuego() - getDia_insercion();

        if(edad < 3){
            System.out.println(getNombre() + "es demasiado joven para producir.");
            cant = 0;
        }
        else if(edad > 40){
            cant = 40;
        }
        else{
            cant = 2;
        }

        if(cant > 0){
            DBManagement.setCantidadDB("productos",
                    DBManagement.getCantidadDB("productos",
                            getProducto().getNombre()) + cant, getProducto().getNombre());
            DBManagement.tablaHistorial("produccion", getId(), cant);
        }
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
        System.out.println("| " + getId() + "         | " + getTipo() + "   | " + alimentado + "            | "
                + getAlimento().getNombre() + "      | " + getProducto().getNombre() + "    |");
    }

}
