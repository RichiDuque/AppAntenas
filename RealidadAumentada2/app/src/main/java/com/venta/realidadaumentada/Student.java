package com.venta.realidadaumentada;

import java.util.List;

public class Student {
    private String name;
    private int codigoClase;
    private List<String> notas;

    public Student(String name, int codigoClase, List<String> notas) {
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

    public List<String> getNotas() {
        return notas;
    }
}

