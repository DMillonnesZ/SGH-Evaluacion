package com.tecsup.sgh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "antecedente_familiar")
@PrimaryKeyJoinColumn(name = "id_antecedente_familiar")
public class AntecedenteFamiliar extends Antecedente {

    @NotBlank(message = "El parentesco es obligatorio")
    @Size(max = 50)
    @Column(name = "parentesco", nullable = false, length = 50)
    private String parentesco;

    @NotBlank(message = "La enfermedad es obligatoria")
    @Size(max = 100)
    @Column(name = "enfermedad", nullable = false, length = 100)
    private String enfermedad;

    @Size(max = 300)
    @Column(name = "observacion", length = 300)
    private String observacion;

    public AntecedenteFamiliar() {
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}