package com.tecsup.sgh.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoSangre {
    A_POSITIVO("A+"),
    O_POSITIVO("O+"),
    B_POSITIVO("B+"),
    AB_POSITIVO("AB+"),
    A_NEGATIVO("A-"),
    O_NEGATIVO("O-"),
    B_NEGATIVO("B-"),
    AB_NEGATIVO("AB-");

    private final String etiqueta;

    TipoSangre(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @JsonValue
    public String getEtiqueta() {
        return etiqueta;
    }

    @JsonCreator
    public static TipoSangre desdeEtiqueta(String etiqueta) {
        for (TipoSangre tipo : values()) {
            if (tipo.etiqueta.equalsIgnoreCase(etiqueta)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de sangre invalido: " + etiqueta
                + ". Valores aceptados: A+, A-, B+, B-, AB+, AB-, O+, O-");
    }
}