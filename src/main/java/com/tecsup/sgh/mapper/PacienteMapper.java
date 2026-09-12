package com.tecsup.sgh.mapper;

import com.tecsup.sgh.dto.PacienteRequestDTO;
import com.tecsup.sgh.dto.PacienteResponseDTO;
import com.tecsup.sgh.model.Paciente;
import com.tecsup.sgh.model.TipoDocumento;
import com.tecsup.sgh.model.Ubigeo;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {

    public Paciente aEntidad(PacienteRequestDTO dto) {
        Paciente paciente = new Paciente();

        TipoDocumento tipoDocumento = new TipoDocumento();
        tipoDocumento.setIdTipoDocumento(dto.getIdTipoDocumento());
        paciente.setTipoDocumento(tipoDocumento);

        Ubigeo ubigeo = new Ubigeo();
        ubigeo.setIdUbigeo(dto.getIdUbigeo());
        paciente.setUbigeo(ubigeo);

        paciente.setNumeroDocumento(dto.getNumeroDocumento());
        paciente.setNombres(dto.getNombres());
        paciente.setApellidoPaterno(dto.getApellidoPaterno());
        paciente.setApellidoMaterno(dto.getApellidoMaterno());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setSexo(dto.getSexo());
        paciente.setEstadoCivil(dto.getEstadoCivil());
        paciente.setTelefono(dto.getTelefono());
        paciente.setCorreoElectronico(dto.getCorreoElectronico());
        paciente.setDireccion(dto.getDireccion());
        paciente.setOcupacion(dto.getOcupacion());
        paciente.setTipoSangre(dto.getTipoSangre());
        paciente.setFotografiaUrl(dto.getFotografiaUrl());

        return paciente;
    }

    public PacienteResponseDTO aResponseDTO(Paciente paciente) {
        PacienteResponseDTO dto = new PacienteResponseDTO();

        dto.setCodigoPaciente(paciente.getCodigoPaciente());
        dto.setTipoDocumento(paciente.getTipoDocumento().getNombre());
        dto.setNumeroDocumento(paciente.getNumeroDocumento());
        dto.setNombres(paciente.getNombres());
        dto.setApellidoPaterno(paciente.getApellidoPaterno());
        dto.setApellidoMaterno(paciente.getApellidoMaterno());
        dto.setFechaNacimiento(paciente.getFechaNacimiento());
        dto.setEdad(paciente.getEdad());
        dto.setSexo(paciente.getSexo());
        dto.setEstadoCivil(paciente.getEstadoCivil());
        dto.setTelefono(paciente.getTelefono());
        dto.setCorreoElectronico(paciente.getCorreoElectronico());
        dto.setDireccion(paciente.getDireccion());
        dto.setDistrito(paciente.getUbigeo().getDistrito());
        dto.setProvincia(paciente.getUbigeo().getProvincia());
        dto.setDepartamento(paciente.getUbigeo().getDepartamento());
        dto.setOcupacion(paciente.getOcupacion());
        dto.setTipoSangre(paciente.getTipoSangre());
        dto.setEstado(paciente.getEstado());
        dto.setFotografiaUrl(paciente.getFotografiaUrl());
        dto.setFechaRegistro(paciente.getFechaRegistro());

        return dto;
    }
}