package General;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;

public class Tienda {

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

    public void mostrarTienda(){

        for (Semilla semilla : getTienda()) {
            System.out.print(semilla.getNombre() + " | ");
        }

    }
}
