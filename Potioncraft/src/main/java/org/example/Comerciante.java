package org.example;

import jakarta.persistence.*;

@Entity
public class Comerciante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoComerciante tipo;

    public Comerciante() {}

    public Comerciante(String nombre, TipoComerciante tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoComerciante getTipo() {
        return tipo;
    }

    public void setTipo(TipoComerciante tipo) {
        this.tipo = tipo;
    }
}
