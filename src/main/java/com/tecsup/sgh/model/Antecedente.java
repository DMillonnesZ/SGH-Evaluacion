package com.tecsup.sgh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "antecedente")
public abstract class Antecedente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_antecedente")
    private Integer idAntecedente;

    @NotNull(message = "El paciente es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    public Antecedente() {
    }

    public Integer getIdAntecedente() {
        return idAntecedente;
    }

    public void setIdAntecedente(Integer idAntecedente) {
        this.idAntecedente = idAntecedente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}