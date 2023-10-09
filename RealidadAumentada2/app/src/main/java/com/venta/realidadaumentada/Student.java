package com.venta.realidadaumentada;

import java.util.List;

public class Student {
    private String name;
    private int codigoClase;
    private List<Double> notas;

    public Student(String name, int codigoClase, List<Double> notas) {
        this.name = name;
        this.codigoClase = codigoClase;
        this.notas = notas;
    }

    public String getName() {
        return name;
    }

    public int getCodigoClase() {
        return codigoClase;
    }

    public List<Double> getNotas() {
        return notas;
    }
}

