package org.example;

import jakarta.persistence.*;

@Entity
public class InventarioIngrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "ingrediente_id", nullable = false, unique = true)
    private Ingrediente ingrediente;

    @Column(nullable = false)
    private Integer cantidad;

    public InventarioIngrediente() {}

    public InventarioIngrediente(Ingrediente ingrediente, Integer cantidad) {
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
