package com.tecsup.sgh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ubigeo")
public class Ubigeo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubigeo")
    private Integer idUbigeo;

    @NotBlank(message = "El distrito es obligatorio")
    @Size(max = 100, message = "El distrito no puede superar los 100 caracteres")
    @Column(name = "distrito", nullable = false, length = 100)
    private String distrito;

    @NotBlank(message = "La provincia es obligatoria")
    @Size(max = 100, message = "La provincia no puede superar los 100 caracteres")
    @Column(name = "provincia", nullable = false, length = 100)
    private String provincia;

    @NotBlank(message = "El departamento es obligatorio")
    @Size(max = 100, message = "El departamento no puede superar los 100 caracteres")
    @Column(name = "departamento", nullable = false, length = 100)
    private String departamento;

    public Ubigeo() {
    }

    public Ubigeo(String distrito, String provincia, String departamento) {
        this.distrito = distrito;
        this.provincia = provincia;
        this.departamento = departamento;
    }

    public Integer getIdUbigeo() {
        return idUbigeo;
    }

    public void setIdUbigeo(Integer idUbigeo) {
        this.idUbigeo = idUbigeo;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}