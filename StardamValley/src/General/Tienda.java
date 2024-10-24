package General;

import Utils.PropertiesF;

import java.io.Serializable;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Random;

import static General.Main.eleccion;
import static java.lang.Integer.parseInt;

public class Tienda implements Serializable {

    private HashSet<Semilla> tienda;

    public Tienda() {
        this.tienda = new HashSet<>();
    }

    public HashSet<Semilla> getTienda() {
        return tienda;
    }

    public void setTienda(HashSet<Semilla> tienda) {
        this.tienda = tienda;
    }

    /**
     * Añade la semillas a la tienda
     *
     * @param semillas Lista de semillas
     */
    public void nuevasSemillas(ArrayList<Semilla> semillas) {

        Random random = new Random();
        HashSet<Semilla> s = new HashSet<>();

        int index1 = random.nextInt(semillas.size());
        int index2;
        int index3;

        do {
            index2 = random.nextInt(semillas.size());
        } while (index2 == index1);

        do {
            index3 = random.nextInt(semillas.size());
        } while (index3 == index1 || index3 == index2);

        s.add(semillas.get(index1));
        s.add(semillas.get(index2));
        s.add(semillas.get(index3));

        setTienda(s);
    }

    /**
     * Muestra la informacion de la tienda
     *
     */
    public void mostrarTienda(){

        for (Semilla semilla : getTienda()) {
            System.out.print(semilla.getNombre() + " | ");
        }
    }

    /**
     * Toma la semilla que se va a introducir en el huerto.
     *
     * @param g Nuestra granja
     * @param eleccion El nombre de la semilla seleccionada por el usuario.
     * @return La semilla que se va a plantar en el huerto
     */
    public Semilla comprarSemillas(Granja g, String eleccion){

        int nSemillas = parseInt(PropertiesF.tomarValor("columnas"));
        int coste = 0;
        boolean enc = false;
        Semilla aux = null;

        for (Semilla semilla : getTienda()) {
            if(semilla.getNombre().equalsIgnoreCase(eleccion)){
                coste = semilla.getPrecioCompra() * nSemillas;
                aux = semilla;
                enc = true;
            }
        }

        if(enc){
            if(coste < g.getDinero()){

                g.setDinero(g.getDinero() - coste);
                return aux;
            }
            else {
                System.out.println("No tienes suficiente dinero para comprar esta semilla.");
                aux = null;
                return aux;
            }
        }
        else{
            System.out.println("No se ha encontrado la semilla selaccionada.");
            return aux;
        }
    }
}
