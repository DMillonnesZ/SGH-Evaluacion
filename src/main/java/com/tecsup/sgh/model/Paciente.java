package com.tecsup.sgh.model;

import com.tecsup.sgh.model.enums.EstadoCivil;
import com.tecsup.sgh.model.enums.EstadoPaciente;
import com.tecsup.sgh.model.enums.Sexo;
import com.tecsup.sgh.model.enums.TipoSangre;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Table(name = "paciente")
@PrimaryKeyJoinColumn(name = "id_paciente")
public class Paciente extends Persona {

    @NotBlank(message = "El código de paciente es obligatorio")
    @Size(max = 20)
    @Column(name = "codigo_paciente", nullable = false, unique = true, length = 20)
    private String codigoPaciente;

    @NotNull(message = "El tipo de documento es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;

    @NotBlank(message = "El número de documento es obligatorio")
    @Size(max = 20)
    @Column(name = "numero_documento", nullable = false, unique = true, length = 20)
    private String numeroDocumento;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 100)
    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(max = 100)
    @Column(name = "apellido_paterno", nullable = false, length = 100)
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno es obligatorio")
    @Size(max = 100)
    @Column(name = "apellido_materno", nullable = false, length = 100)
    private String apellidoMaterno;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @NotNull(message = "El sexo es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false, length = 20)
    private Sexo sexo;

    @NotNull(message = "El estado civil es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil", nullable = false, length = 20)
    private EstadoCivil estadoCivil;

    @NotNull(message = "El ubigeo es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ubigeo", nullable = false)
    private Ubigeo ubigeo;

    @Size(max = 100)
    @Column(name = "ocupacion", length = 100)
    private String ocupacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_sangre", length = 15)
    private TipoSangre tipoSangre;

    @NotNull(message = "El estado del paciente es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoPaciente estado;

    @Column(name = "fotografia_url", length = 255)
    private String fotografiaUrl;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    public Paciente() {
    }

    @PrePersist
    protected void alPersistir() {
        this.fechaRegistro = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = EstadoPaciente.ACTIVO;
        }
    }

    @Transient
    public Integer getEdad() {
        if (fechaNacimiento == null) {
            return null;
        }
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public String getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(String codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Ubigeo getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public TipoSangre getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(TipoSangre tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public EstadoPaciente getEstado() {
        return estado;
    }

    public void setEstado(EstadoPaciente estado) {
        this.estado = estado;
    }

    public String getFotografiaUrl() {
        return fotografiaUrl;
    }

    public void setFotografiaUrl(String fotografiaUrl) {
        this.fotografiaUrl = fotografiaUrl;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
}