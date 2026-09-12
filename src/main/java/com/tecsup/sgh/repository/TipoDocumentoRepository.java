package com.tecsup.sgh.repository;

import com.tecsup.sgh.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer> {

    Optional<TipoDocumento> findByNombreIgnoreCase(String nombre);
}