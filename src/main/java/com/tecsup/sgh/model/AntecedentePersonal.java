package com.tecsup.sgh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "antecedente_personal")
@PrimaryKeyJoinColumn(name = "id_antecedente_personal")
public class AntecedentePersonal extends Antecedente {

    @NotBlank(message = "El tipo de antecedente es obligatorio")
    @Size(max = 50)
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 300)
    @Column(name = "descripcion", nullable = false, length = 300)
    private String descripcion;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    public AntecedentePersonal() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}