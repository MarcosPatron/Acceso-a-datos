package org.example;

import jakarta.persistence.*;


@Entity
public class InventarioPocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "pocion_id", nullable = false, unique = true)
    private Pocion pocion;

    @Column(nullable = false)
    private Integer cantidad;

    public InventarioPocion() {}

    public InventarioPocion(Pocion pocion, Integer cantidad) {
        this.pocion = pocion;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pocion getPocion() {
        return pocion;
    }

    public void setPocion(Pocion pocion) {
        this.pocion = pocion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
