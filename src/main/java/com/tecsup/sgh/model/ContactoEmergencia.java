package com.tecsup.sgh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "contacto_emergencia")
@PrimaryKeyJoinColumn(name = "id_contacto")
public class ContactoEmergencia extends Persona {

    @NotNull(message = "El paciente es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 150)
    @Column(name = "nombre_completo", nullable = false, length = 150)
    private String nombreCompleto;

    @NotBlank(message = "El parentesco es obligatorio")
    @Size(max = 50)
    @Column(name = "parentesco", nullable = false, length = 50)
    private String parentesco;

    @Column(name = "es_principal", nullable = false)
    private Boolean esPrincipal = false;

    public ContactoEmergencia() {
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }
}