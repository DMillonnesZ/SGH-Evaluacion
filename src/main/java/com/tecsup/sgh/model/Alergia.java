package com.tecsup.sgh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "alergia")
public class Alergia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alergia")
    private Integer idAlergia;

    @NotNull(message = "El paciente es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @NotBlank(message = "El tipo de alergia es obligatorio")
    @Size(max = 50)
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 200)
    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @Size(max = 200)
    @Column(name = "reaccion", length = 200)
    private String reaccion;

    public Alergia() {
    }

    public Integer getIdAlergia() {
        return idAlergia;
    }

    public void setIdAlergia(Integer idAlergia) {
        this.idAlergia = idAlergia;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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

    public String getReaccion() {
        return reaccion;
    }

    public void setReaccion(String reaccion) {
        this.reaccion = reaccion;
    }
}