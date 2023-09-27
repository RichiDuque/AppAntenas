package com.venta.realidadaumentada;

import java.util.List;

public class Student {
    private String name;
    private List<Double> notas;

    public Student(String name, List<Double> notas) {
        this.name = name;
        this.notas = notas;
    }

    public String getName() {
        return name;
    }

    public List<Double> getNotas() {
        return notas;
    }
}

