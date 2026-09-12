package com.tecsup.sgh.service.impl;

import com.tecsup.sgh.service.FotoPacienteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class FotoPacienteServiceImpl implements FotoPacienteService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    private static final List<String> EXTENSIONES_PERMITIDAS = List.of("jpg", "jpeg", "png");
    private static final long TAMANIO_MAXIMO_BYTES = 5 * 1024 * 1024; // 5 MB

    @Override
    public String guardarFoto(String codigoPaciente, MultipartFile archivo) {
        if (archivo == null || archivo.isEmpty()) {
            throw new IllegalArgumentException("Debe adjuntar un archivo de imagen");
        }

        if (archivo.getSize() > TAMANIO_MAXIMO_BYTES) {
            throw new IllegalArgumentException("El archivo no debe superar los 5MB");
        }

        String nombreOriginal = archivo.getOriginalFilename();
        String extension = obtenerExtension(nombreOriginal);

        if (!EXTENSIONES_PERMITIDAS.contains(extension.toLowerCase())) {
            throw new IllegalArgumentException("Solo se permiten imagenes JPG o PNG");
        }

        try {
            Path directorio = Paths.get(uploadDir);
            if (!Files.exists(directorio)) {
                Files.createDirectories(directorio);
            }

            String nombreArchivo = codigoPaciente + "." + extension;
            Path rutaDestino = directorio.resolve(nombreArchivo);

            Files.copy(archivo.getInputStream(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);

            return "/fotos/" + nombreArchivo;

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la foto del paciente: " + e.getMessage(), e);
        }
    }

    private String obtenerExtension(String nombreArchivo) {
        if (nombreArchivo == null || !nombreArchivo.contains(".")) {
            throw new IllegalArgumentException("El archivo debe tener una extension valida");
        }
        return nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1);
    }
}