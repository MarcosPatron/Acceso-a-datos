package org.example;

import jakarta.persistence.*;

import javax.xml.namespace.QName;
import java.util.List;

@Entity
@Table(name = "Mision")
public class Mision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recompensa_id", nullable = false)
    private Recompensa recompensa;

    @ManyToMany
    @JoinTable(
            name = "Jugador_Mision",
            joinColumns = @JoinColumn( name = "mision_id"),
            inverseJoinColumns = @JoinColumn( name = "jugador_id")
    )
    private List<Jugador> jugadores;

    public Mision() {
    }

    public Mision(String descripcion, Recompensa recompensa, List<Jugador> jugadores) {
        this.descripcion = descripcion;
        this.recompensa = recompensa;
        this.jugadores = jugadores;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Recompensa getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(Recompensa recompensa) {
        this.recompensa = recompensa;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}

