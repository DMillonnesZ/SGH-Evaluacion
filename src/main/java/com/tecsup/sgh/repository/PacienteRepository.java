package com.tecsup.sgh.repository;

import com.tecsup.sgh.model.Paciente;
import com.tecsup.sgh.model.enums.EstadoPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    boolean existsByNumeroDocumento(String numeroDocumento);

    Optional<Paciente> findByNumeroDocumento(String numeroDocumento);

    Optional<Paciente> findByCodigoPaciente(String codigoPaciente);

    List<Paciente> findByNombresContainingIgnoreCase(String nombres);

    List<Paciente> findByApellidoPaternoContainingIgnoreCaseOrApellidoMaternoContainingIgnoreCase(
            String apellidoPaterno, String apellidoMaterno);

    List<Paciente> findByTelefono(String telefono);

    @Query("""
            SELECT p FROM Paciente p
            WHERE p.numeroDocumento LIKE CONCAT('%', :texto, '%')
               OR p.codigoPaciente LIKE CONCAT('%', :texto, '%')
               OR LOWER(p.nombres) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR LOWER(p.apellidoPaterno) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR LOWER(p.apellidoMaterno) LIKE LOWER(CONCAT('%', :texto, '%'))
            """)
    List<Paciente> buscarPorTexto(@Param("texto") String texto);

    List<Paciente> findByEstado(EstadoPaciente estado);

    long countByEstado(EstadoPaciente estado);
}