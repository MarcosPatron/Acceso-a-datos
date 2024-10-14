package General;

import java.io.Serializable;
import java.time.LocalDate;

public class Deportista implements Serializable {

    private String dni;
    private String nombre;
    private LocalDate fechaNac;
    private Deporte deporte;

    public Deportista(String dni, String nombre, String fechaNac, Deporte deporte) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNac = LocalDate.parse(fechaNac);
        this.deporte = deporte;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    @Override
    public String toString() {
        return "Deportista{" +
                "DNI:'" + dni + '\'' +
                ", Nombre:'" + nombre + '\'' +
                ", Fecha de Nacimiento:" + fechaNac +
                ", Deporte:" + deporte +
                '}';
    }
}
