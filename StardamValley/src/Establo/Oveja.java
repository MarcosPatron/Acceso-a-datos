package Establo;

public class Oveja extends Animal {

    public Oveja(int id, Tipo tipo, String nombre, Alimento alimento, Producto producto) {
        super(id, tipo, nombre, alimento, producto);
    }

    @Override
    public void producir(){

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
