package Establo;

import General.Granja;
import Utils.DBManagement;

import java.time.LocalDate;

public class Oveja extends Animal {

    private LocalDate fechaEsquilado;

    public Oveja(int id, Tipo tipo, String nombre, Alimento alimento, Producto producto) {
        super(id, tipo, nombre, alimento, producto);
        fechaEsquilado = null;
    }

    public LocalDate getFechaEsquilado() {
        return fechaEsquilado;
    }

    public void setFechaEsquilado(LocalDate fechaEsquilado) {
        this.fechaEsquilado = fechaEsquilado;
    }

    @Override
    public void producir(Granja g){

        int cant = 5;
        LocalDate hoy = LocalDate.now();

        if(fechaEsquilado == null || hoy.getDayOfYear() - fechaEsquilado.getDayOfYear() >= 2){
            fechaEsquilado = hoy;

            DBManagement.setCantidadDB("productos",
                    DBManagement.getCantidadDB("productos",
                            getProducto().getNombre()) + cant, getProducto().getNombre());
            DBManagement.tablaHistorial("produccion", getId(), cant);
        }
        else{
            System.out.println("Todavia no puedes esquilar a " + getNombre());
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
        System.out.println("| " + getId() + "         | " + getTipo() + "     | " + alimentado + "            | "
                + getAlimento().getNombre() + "     | " + getProducto().getNombre() + "      |");
    }
}
