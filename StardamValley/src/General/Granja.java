package General;

import Utils.PropertiesF;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class Granja implements Serializable {

    private int diaJuego;
    private int dinero;
    private Estaciones estacion;
    private Tienda semillasVenta;
    private Almacen almacen;

    public Granja(int dinero, Estaciones estacion){
        this.diaJuego = 0;
        this.dinero = dinero;
        this.estacion = estacion;
        this.semillasVenta = new Tienda();
        this.almacen = new Almacen();
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

    public Tienda getSemillasVenta() {
        return semillasVenta;
    }

    public void setSemillasVenta(Tienda semillasVenta) {
        this.semillasVenta = semillasVenta;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    /**
     * Muestra la información de la granaja
     */
    public void mostrarGranja(){

        System.out.print("INFORMACIÓN DE LA GRANJA:" +
                "\n - Día de juego: " + getDiaJuego() +
                "\n - Dinero disponible: " + getDinero() +
                "\n - Estación: " + getEstacion());
        System.out.print("\n - Semillas en venta: ");
        this.semillasVenta.mostrarTienda();
        System.out.println("\n - Frutos en almacen: ");
        this.almacen.mostrarAlmacen();
        System.out.println("\n - Estado del huerto: ");
        Huerto.mostrarHuerto();
    }

    /**
     * Inicia un nuevo dia en nuestro juego
     *
     * @param sem La lista de las semillas disponibles en al juego
     */
    public void nuevoDia(Map<String, ArrayList<Semilla>> sem){

        int duracion = parseInt(PropertiesF.tomarValor("duracion_estacion"));

        setDiaJuego(this.diaJuego + 1);
        if(this.diaJuego > duracion) {

            setDiaJuego(1);

            switch (this.estacion) {
                case Estaciones.Primavera:
                    setEstacion(Estaciones.Verano);
                    break;
                case Estaciones.Verano:
                    setEstacion(Estaciones.Otono);
                    break;
                case Estaciones.Otono:
                    setEstacion(Estaciones.Invierno);
                    break;
                case Estaciones.Invierno:
                    setEstacion(Estaciones.Primavera);
                    break;
            }
        }
        else{
            Huerto.nuevoDiaHuerto();
        }
        this.semillasVenta.nuevasSemillas(sem.get(this.estacion.name()));
    }

}
