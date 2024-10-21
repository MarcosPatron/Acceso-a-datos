package General;

import Utils.Constantes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class Granja implements Serializable {

    private int diaJuego;
    private int dinero;
    private Estaciones estacion;
    private Tienda semillasVenta;
    private Almacen almacen;

    public Granja(int dinero, Estaciones estacion, Almacen almacen) {
        this.diaJuego = 0;
        this.dinero = dinero;
        this.estacion = estacion;
        this.semillasVenta = new Tienda();
        this.almacen = almacen;
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

    public void nuevoDia(Map<String, ArrayList> sem){

        setDiaJuego(this.diaJuego + 1);
        if(this.diaJuego > 30) {

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
            Huerto.crearHuerto();
        }
        else{
            Huerto.nuevoDiaHuerto();
        }
        this.semillasVenta.nuevasSemillas(sem.get(this.estacion.name()));
    }

}
