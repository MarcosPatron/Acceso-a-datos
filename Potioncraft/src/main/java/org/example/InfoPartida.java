package org.example;

import jakarta.persistence.*;

@Entity
public class InfoPartida{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "DOUBLE DEFAULT 500")
    private Double oro;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer reputacion;


    public InfoPartida() {
        this.oro = 500.0;
        this.reputacion = 0;
    }

    public InfoPartida(Double oro, Integer reputacion) {
        this.oro = oro;
        this.reputacion = reputacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getOro() {
        return oro;
    }

    public void setOro(Double oro) {
        this.oro = oro;
    }

    public Integer getReputacion() {
        return reputacion;
    }

    public void setReputacion(Integer reputacion) {
        this.reputacion = reputacion;
    }
}
