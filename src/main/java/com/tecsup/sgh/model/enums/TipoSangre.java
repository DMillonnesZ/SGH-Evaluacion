package com.tecsup.sgh.model.enums;

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

    public String getEtiqueta() {
        return etiqueta;
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}