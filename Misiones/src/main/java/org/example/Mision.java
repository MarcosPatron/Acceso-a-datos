package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Mision")
@NamedQuery(
        name = "Mision.findAll",
        query = "SELECT m FROM Mision m"
)
public class Mision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recompensa_id", nullable = false)
    private List<Recompensa> recompensa;

    @ManyToMany
    @JoinTable(
            name = "Jugador_Mision",
            joinColumns = @JoinColumn( name = "mision_id"),
            inverseJoinColumns = @JoinColumn( name = "jugador_id")
    )
    private List<Jugador> jugadores;

    public Mision() {
    }

    public Mision(String descripcion) {
        this.descripcion = descripcion;
        this.recompensa = new ArrayList<>();
        this.jugadores = new ArrayList<>();
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

    public List<Recompensa> getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(List <Recompensa> recompensa) {
        this.recompensa = recompensa;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}

