package com.tecsup.sgh.service;

import com.tecsup.sgh.dto.PacienteRequestDTO;
import com.tecsup.sgh.dto.PacienteResponseDTO;
import com.tecsup.sgh.model.enums.EstadoPaciente;

import java.util.List;

public interface PacienteService {

    PacienteResponseDTO registrar(PacienteRequestDTO dto);

    List<PacienteResponseDTO> listarTodos();

    PacienteResponseDTO buscarPorCodigoPaciente(String codigoPaciente);

    PacienteResponseDTO buscarPorNumeroDocumento(String numeroDocumento);

    List<PacienteResponseDTO> buscarPorTexto(String texto);

    PacienteResponseDTO actualizarPorCodigo(String codigoPaciente, PacienteRequestDTO dto);

    void cambiarEstadoPorCodigo(String codigoPaciente, EstadoPaciente estado);

    PacienteResponseDTO actualizarFoto(String codigoPaciente, org.springframework.web.multipart.MultipartFile archivo);
}