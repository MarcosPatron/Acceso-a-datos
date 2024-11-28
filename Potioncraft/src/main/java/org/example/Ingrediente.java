package org.example;

import jakarta.persistence.*;
import org.example.TipoIngrediente;


@Entity
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TipoIngrediente tipo;

    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(precision = 6, scale = 2)
    private Double precioCompra;

    private String efectoPositivo;
    private String efectoNegativo;

    private Integer nivelToxicidad;

    @Column(precision = 4, scale = 2)
    private Double dureza;

    public Ingrediente() {}

    public Ingrediente(TipoIngrediente tipo, String nombre, String descripcion, Double precioCompra,
                       String efectoPositivo, String efectoNegativo, Integer nivelToxicidad, Double dureza) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.efectoPositivo = efectoPositivo;
        this.efectoNegativo = efectoNegativo;
        this.nivelToxicidad = nivelToxicidad;
        this.dureza = dureza;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoIngrediente getTipo() {
        return tipo;
    }

    public void setTipo(TipoIngrediente tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getEfectoPositivo() {
        return efectoPositivo;
    }

    public void setEfectoPositivo(String efectoPositivo) {
        this.efectoPositivo = efectoPositivo;
    }

    public String getEfectoNegativo() {
        return efectoNegativo;
    }

    public void setEfectoNegativo(String efectoNegativo) {
        this.efectoNegativo = efectoNegativo;
    }

    public Integer getNivelToxicidad() {
        return nivelToxicidad;
    }

    public void setNivelToxicidad(Integer nivelToxicidad) {
        this.nivelToxicidad = nivelToxicidad;
    }

    public Double getDureza() {
        return dureza;
    }

    public void setDureza(Double dureza) {
        this.dureza = dureza;
    }
}
