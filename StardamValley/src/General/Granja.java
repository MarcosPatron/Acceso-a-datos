package General;

import java.util.ArrayList;

public class Granja {

    private int diaJuego;
    private int dinero;
    private Estaciones estacion;
    private String[] semillasVenta;
    private ArrayList<Semillas> frutas;

    public Granja(int dinero, Estaciones estacion, String[] semillasVenta, ArrayList<Semillas> frutas) {
        this.diaJuego = 0;
        this.dinero = dinero;
        this.estacion = estacion;
        this.semillasVenta = semillasVenta;
        this.frutas = frutas;
    }

    public int getDiaJuego() {
        return diaJuego;
    }

    public void setDiaJuego(int diaJuego) {
        this.diaJuego = diaJuego;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public Estaciones getEstacion() {
        return estacion;
    }

    public void setEstacion(Estaciones estacion) {
        this.estacion = estacion;
    }

    public String[] getSemillasVenta() {
        return semillasVenta;
    }

    public void setSemillasVenta(String[] semillasVenta) {
        this.semillasVenta = semillasVenta;
    }

    public ArrayList<Semillas> getFrutas() {
        return frutas;
    }

    public void setFrutas(ArrayList<Semillas> frutas) {
        this.frutas = frutas;
    }


}
