package com.tecsup.sgh.service;

import org.springframework.web.multipart.MultipartFile;

public interface FotoPacienteService {

    String guardarFoto(String codigoPaciente, MultipartFile archivo);
}