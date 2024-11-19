package org.example;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Animales")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column(name = "dia_insercion", nullable = false)
    private LocalDate diaInsercion;

    @Column(nullable = false)
    private Double peso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    public Animal() {
    }

    public Animal(LocalDate diaInsercion, String nombre, Double peso, Tipo tipo) {
        this.nombre = nombre;
        this.diaInsercion = diaInsercion;
        this.peso = peso;
        this.tipo = tipo;
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

    public LocalDate getDiaInsercion() {
        return diaInsercion;
    }

    public void setDiaInsercion(LocalDate dia_insercion) {
        this.diaInsercion = dia_insercion;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
