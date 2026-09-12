package com.tecsup.sgh.repository;

import com.tecsup.sgh.model.Ubigeo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UbigeoRepository extends JpaRepository<Ubigeo, Integer> {

    List<Ubigeo> findByDepartamentoIgnoreCase(String departamento);

    List<Ubigeo> findByDepartamentoIgnoreCaseAndProvinciaIgnoreCase(String departamento, String provincia);
}