package org.example;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Recompensa")
@NamedQuery(
        name = "Recompensa.findAll",
        query = "SELECT r FROM Recompensa r"
)
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TipoRecompensa tipo;

    @OneToMany(mappedBy = "recompensa", cascade = CascadeType.ALL)
    private List<Mision> misiones;

    public Recompensa() {
    }

    public Recompensa(String nombre, TipoRecompensa tipo, List<Mision> misiones) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.misiones = misiones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoRecompensa getTipo() {
        return tipo;
    }

    public void setTipo(TipoRecompensa tipo) {
        this.tipo = tipo;
    }

    public List<Mision> getMisiones() {
        return misiones;
    }

    public void setMisiones(List<Mision> misiones) {
        this.misiones = misiones;
    }
}

