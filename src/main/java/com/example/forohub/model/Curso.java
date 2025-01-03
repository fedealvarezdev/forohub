package com.example.forohub.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Curso {
    GEOMETRIA("geometria"),
    MATEMATICA("matematica"),
    LITERATURA("literatura"),
    HISTORIA("historia"),
    COMPUTACION("computacion");

    private final String value;

    Curso(String value){
        this.value = value;
    }

    @JsonValue
    public String getValor() {
        return value;
    }

    @JsonCreator
    public static Curso fromValue(String value) {
        for (Curso nombreCurso : Curso.values()) {
            if (nombreCurso.value.equalsIgnoreCase(value)) {
                return nombreCurso;
            }
        }
        throw new IllegalArgumentException("Curso no válidO: " + value);
    }
}
