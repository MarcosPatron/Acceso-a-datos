package Establo;

import General.Granja;
import Utils.DBManagement;

import java.util.ArrayList;

public class Alimento {

    private String nombre;
    private double precio;

    public Alimento(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public static void mostrarAlimentos(){

        Alimento a;

        System.out.println(" -----------------------------------------" +
                         "\n| Alimentos       | Cantidad Disponible   |" +
                         "\n -----------------------------------------");

        for (int i = 1; i <= DBManagement.tamanoTabla("alimentos"); i++) {

           a = DBManagement.cargarAlimento(i);

            System.out.printf("| %-15s | %-21d |%n", a.nombre, DBManagement.getCantidadDB("alimentos", a.nombre));

        }
        System.out.println(" -----------------------------------------");
    }

    public static void alimentar(Granja g, ArrayList<Animal> animales){

        for (int i = 0; i < animales.size(); i++) {
            int cant = 1;
            if(!animales.get(i).isAlimentado() &&
                    DBManagement.getCantidadDB("alimentos", animales.get(i).getAlimento().getNombre()) > 0){

                animales.get(i).setAlimentado(true);

                if(animales.get(i).getTipo() == Tipo.VACA){

                    int edad = g.getDiaJuego() - animales.get(i).getDia_insercion();

                    if(edad < 10){
                        cant = 1;
                    } else if (edad > 40) {
                        cant = 2;
                    }
                    else {
                        cant = 3;
                    }
                }
                DBManagement.setCantidadDB("alimentos",
                        DBManagement.getCantidadDB("alimentos",
                                animales.get(i).getAlimento().nombre) - cant, animales.get(i).getAlimento().nombre);
                DBManagement.tablaHistorial("consumo", animales.get(i).getId(), cant);
            }
            else{
                System.out.println("No se ha podido alimentar a " + animales.get(i).getNombre());
            }
        }
    }

}
