package com.tecsup.sgh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persona")
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d{6,15}", message = "El teléfono debe tener entre 6 y 15 dígitos")
    @Column(name = "telefono", nullable = false, length = 15)
    private String telefono;

    @Email(message = "El correo electrónico no tiene un formato válido")
    @Column(name = "correo_electronico", length = 100)
    private String correoElectronico;

    @Size(max = 200)
    @Column(name = "direccion", length = 200)
    private String direccion;

    public Persona() {
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}