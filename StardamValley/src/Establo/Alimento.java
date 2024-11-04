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

        for (Animal animal : animales) {
            int cant = 1;
            if (!animal.isAlimentado() &&
                    DBManagement.getCantidadDB("alimentos", animal.getAlimento().getNombre()) > 0) {

                animal.setAlimentado(true);

                if (animal.getTipo() == Tipo.VACA) {

                    int edad = g.getDiaJuego() - animal.getDia_insercion();

                    if (edad < 10) {
                        cant = 1;
                    } else if (edad > 40) {
                        cant = 2;
                    } else {
                        cant = 3;
                    }
                }
                DBManagement.setCantidadDB("alimentos",
                        DBManagement.getCantidadDB("alimentos",
                                animal.getAlimento().nombre) - cant, animal.getAlimento().nombre);
                DBManagement.tablaHistorial("consumo", animal.getId(), cant);
            } else {
                System.out.println("No se ha podido alimentar a " + animal.getNombre());
            }
        }
    }

    public static void rellenarComedero(){

        System.out.println("Rellenando comederos...");

        DBManagement.setCantidadDB("alimentos", 25, "Maiz");
        System.out.println("- Se ha rellenado la tolva de Maiz");

        DBManagement.setCantidadDB("alimentos", 25, "Avena");
        System.out.println("- Se ha rellenado la tolva de Avena");

        DBManagement.setCantidadDB("alimentos", 25, "Heno");
        System.out.println("- Se ha rellenado la tolva de Heno");
    }

}
