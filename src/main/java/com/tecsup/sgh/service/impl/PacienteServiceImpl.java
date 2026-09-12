package com.tecsup.sgh.service.impl;

import com.tecsup.sgh.dto.PacienteRequestDTO;
import com.tecsup.sgh.dto.PacienteResponseDTO;
import com.tecsup.sgh.mapper.PacienteMapper;
import com.tecsup.sgh.model.Paciente;
import com.tecsup.sgh.model.TipoDocumento;
import com.tecsup.sgh.model.Ubigeo;
import com.tecsup.sgh.model.enums.EstadoPaciente;
import com.tecsup.sgh.repository.PacienteRepository;
import com.tecsup.sgh.repository.TipoDocumentoRepository;
import com.tecsup.sgh.repository.UbigeoRepository;
import com.tecsup.sgh.service.FotoPacienteService;
import com.tecsup.sgh.service.PacienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final UbigeoRepository ubigeoRepository;
    private final PacienteMapper pacienteMapper;
    private final FotoPacienteService fotoPacienteService;


    public PacienteServiceImpl(PacienteRepository pacienteRepository,
                               TipoDocumentoRepository tipoDocumentoRepository,
                               UbigeoRepository ubigeoRepository,
                               PacienteMapper pacienteMapper,
                               FotoPacienteService fotoPacienteService) {
        this.pacienteRepository = pacienteRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
        this.ubigeoRepository = ubigeoRepository;
        this.pacienteMapper = pacienteMapper;
        this.fotoPacienteService = fotoPacienteService;
    }

    @Override
    @Transactional
    public PacienteResponseDTO registrar(PacienteRequestDTO dto) {
        if (pacienteRepository.existsByNumeroDocumento(dto.getNumeroDocumento())) {
            throw new IllegalArgumentException(
                    "Ya existe un paciente registrado con el numero de documento: " + dto.getNumeroDocumento());
        }

        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(dto.getIdTipoDocumento())
                .orElseThrow(() -> new IllegalArgumentException("El tipo de documento indicado no existe"));

        Ubigeo ubigeo = ubigeoRepository.findById(dto.getIdUbigeo())
                .orElseThrow(() -> new IllegalArgumentException("El ubigeo indicado no existe"));

        Paciente paciente = pacienteMapper.aEntidad(dto);
        paciente.setTipoDocumento(tipoDocumento);
        paciente.setUbigeo(ubigeo);
        paciente.setCodigoPaciente(generarCodigoPaciente());
        paciente.setEstado(EstadoPaciente.ACTIVO);

        Paciente guardado = pacienteRepository.save(paciente);
        return pacienteMapper.aResponseDTO(guardado);
    }

    @Override
    public List<PacienteResponseDTO> listarTodos() {
        return pacienteRepository.findAll().stream()
                .map(pacienteMapper::aResponseDTO)
                .toList();
    }
    @Override
    public PacienteResponseDTO actualizarFoto(String codigoPaciente, MultipartFile archivo) {
        Paciente paciente = obtenerEntidadPorCodigo(codigoPaciente);
        String urlFoto = fotoPacienteService.guardarFoto(codigoPaciente, archivo);
        paciente.setFotografiaUrl(urlFoto);
        Paciente actualizado = pacienteRepository.save(paciente);
        return pacienteMapper.aResponseDTO(actualizado);
    }

    @Override
    public PacienteResponseDTO buscarPorCodigoPaciente(String codigoPaciente) {
        Paciente paciente = obtenerEntidadPorCodigo(codigoPaciente);
        return pacienteMapper.aResponseDTO(paciente);
    }

    @Override
    public PacienteResponseDTO buscarPorNumeroDocumento(String numeroDocumento) {
        Paciente paciente = pacienteRepository.findByNumeroDocumento(numeroDocumento)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontro un paciente con el numero de documento: " + numeroDocumento));
        return pacienteMapper.aResponseDTO(paciente);
    }

    @Override
    public List<PacienteResponseDTO> buscarPorTexto(String texto) {
        return pacienteRepository.buscarPorTexto(texto).stream()
                .map(pacienteMapper::aResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public PacienteResponseDTO actualizarPorCodigo(String codigoPaciente, PacienteRequestDTO dto) {
        Paciente paciente = obtenerEntidadPorCodigo(codigoPaciente);

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

        if (dto.getIdUbigeo() != null) {
            Ubigeo ubigeo = ubigeoRepository.findById(dto.getIdUbigeo())
                    .orElseThrow(() -> new IllegalArgumentException("El ubigeo indicado no existe"));
            paciente.setUbigeo(ubigeo);
        }

        Paciente actualizado = pacienteRepository.save(paciente);
        return pacienteMapper.aResponseDTO(actualizado);
    }

    @Override
    @Transactional
    public void cambiarEstadoPorCodigo(String codigoPaciente, EstadoPaciente estado) {
        Paciente paciente = obtenerEntidadPorCodigo(codigoPaciente);
        paciente.setEstado(estado);
        pacienteRepository.save(paciente);
    }

    private Paciente obtenerEntidadPorCodigo(String codigoPaciente) {
        return pacienteRepository.findByCodigoPaciente(codigoPaciente)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontro un paciente con el codigo: " + codigoPaciente));
    }

    private String generarCodigoPaciente() {
        String codigo;
        do {
            codigo = "PAC-" + UUID.randomUUID().toString()
                    .replace("-", "")
                    .substring(0, 8)
                    .toUpperCase();
        } while (pacienteRepository.findByCodigoPaciente(codigo).isPresent());
        return codigo;
    }
}