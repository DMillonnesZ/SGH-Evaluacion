package com.tecsup.sgh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tipo_documento")
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_documento")
    private Integer idTipoDocumento;

    @NotBlank(message = "El nombre del tipo de documento es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    public TipoDocumento() {
    }

    public TipoDocumento(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}