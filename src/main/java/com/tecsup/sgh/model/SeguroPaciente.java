package com.tecsup.sgh.model;

import com.tecsup.sgh.model.enums.EstadoCobertura;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "seguro_paciente")
public class SeguroPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguro_paciente")
    private Integer idSeguroPaciente;

    @NotNull(message = "El paciente es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @NotBlank(message = "El tipo de seguro es obligatorio")
    @Size(max = 50)
    @Column(name = "tipo_seguro", nullable = false, length = 50)
    private String tipoSeguro;

    @NotBlank(message = "La empresa aseguradora es obligatoria")
    @Size(max = 100)
    @Column(name = "empresa_aseguradora", nullable = false, length = 100)
    private String empresaAseguradora;

    @NotBlank(message = "El número de póliza es obligatorio")
    @Size(max = 50)
    @Column(name = "numero_poliza", nullable = false, length = 50)
    private String numeroPoliza;

    @Size(max = 50)
    @Column(name = "numero_afiliacion", length = 50)
    private String numeroAfiliacion;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de vencimiento es obligatoria")
    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @NotNull(message = "El estado de cobertura es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_cobertura", nullable = false, length = 20)
    private EstadoCobertura estadoCobertura;

    public SeguroPaciente() {
    }

    @AssertTrue(message = "La fecha de vencimiento debe ser posterior a la fecha de inicio")
    private boolean isFechaVencimientoValida() {
        if (fechaInicio == null || fechaVencimiento == null) {
            return true;
        }
        return fechaVencimiento.isAfter(fechaInicio);
    }

    public Integer getIdSeguroPaciente() {
        return idSeguroPaciente;
    }

    public void setIdSeguroPaciente(Integer idSeguroPaciente) {
        this.idSeguroPaciente = idSeguroPaciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(String tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public String getEmpresaAseguradora() {
        return empresaAseguradora;
    }

    public void setEmpresaAseguradora(String empresaAseguradora) {
        this.empresaAseguradora = empresaAseguradora;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public String getNumeroAfiliacion() {
        return numeroAfiliacion;
    }

    public void setNumeroAfiliacion(String numeroAfiliacion) {
        this.numeroAfiliacion = numeroAfiliacion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public EstadoCobertura getEstadoCobertura() {
        return estadoCobertura;
    }

    public void setEstadoCobertura(EstadoCobertura estadoCobertura) {
        this.estadoCobertura = estadoCobertura;
    }
}