package org.example;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Animales")
public class Animal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private Tipo tipo;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "dia_insercion")
    private Integer diaInsercion;

    @Column(name = "peso")
    private Double peso;

    public Animal() {
    }

    public Animal(Integer diaInsercion, String nombre, Double peso, Tipo tipo) {
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

    public Integer getDiaInsercion() {
        return diaInsercion;
    }

    public void setDiaInsercion(Integer dia_insercion) {
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
