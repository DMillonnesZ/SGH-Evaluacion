package com.tecsup.sgh.controller;

import com.tecsup.sgh.dto.PacienteRequestDTO;
import com.tecsup.sgh.dto.PacienteResponseDTO;
import com.tecsup.sgh.model.enums.EstadoPaciente;
import com.tecsup.sgh.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> registrar(@Valid @RequestBody PacienteRequestDTO dto) {
        PacienteResponseDTO creado = pacienteService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @GetMapping("/{codigoPaciente}")
    public ResponseEntity<PacienteResponseDTO> buscarPorCodigoPaciente(@PathVariable String codigoPaciente) {
        return ResponseEntity.ok(pacienteService.buscarPorCodigoPaciente(codigoPaciente));
    }

    @GetMapping("/documento/{numeroDocumento}")
    public ResponseEntity<PacienteResponseDTO> buscarPorNumeroDocumento(@PathVariable String numeroDocumento) {
        return ResponseEntity.ok(pacienteService.buscarPorNumeroDocumento(numeroDocumento));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<PacienteResponseDTO>> buscarPorTexto(@RequestParam String texto) {
        return ResponseEntity.ok(pacienteService.buscarPorTexto(texto));
    }

    @PutMapping("/{codigoPaciente}")
    public ResponseEntity<PacienteResponseDTO> actualizar(@PathVariable String codigoPaciente,
                                                          @Valid @RequestBody PacienteRequestDTO dto) {
        return ResponseEntity.ok(pacienteService.actualizarPorCodigo(codigoPaciente, dto));
    }

    @PatchMapping("/{codigoPaciente}/estado")
    public ResponseEntity<Void> cambiarEstado(@PathVariable String codigoPaciente,
                                              @RequestParam EstadoPaciente estado) {
        pacienteService.cambiarEstadoPorCodigo(codigoPaciente, estado);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{codigoPaciente}/foto", consumes = "multipart/form-data")
    public ResponseEntity<PacienteResponseDTO> subirFoto(@PathVariable String codigoPaciente,
                                                         @RequestParam("archivo") MultipartFile archivo) {
        return ResponseEntity.ok(pacienteService.actualizarFoto(codigoPaciente, archivo));
    }
}