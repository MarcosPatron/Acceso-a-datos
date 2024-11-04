package Establo;

import General.Granja;
import Utils.DBManagement;

public class Gallina extends Animal {

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

        if(g.getAlmacen().getProductos().get(getProducto()) != null){
            cant += g.getAlmacen().getProductos().get(getProducto());
        }

        if(cant == 0){
            g.getAlmacen().getProductos().put(getProducto(), cant);

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
