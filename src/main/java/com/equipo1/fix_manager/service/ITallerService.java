package com.equipo1.fix_manager.service;

import com.equipo1.fix_manager.dto.CrearTallerDTO;
import com.equipo1.fix_manager.dto.TallerResponseDTO;
import com.equipo1.fix_manager.model.Taller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ITallerService {
    Taller crearTallerConImagen(Long usuarioId, CrearTallerDTO datos, MultipartFile imagen) throws IOException;
    TallerResponseDTO obtenerOTallerDeUsuario(Long usuarioId);
}
