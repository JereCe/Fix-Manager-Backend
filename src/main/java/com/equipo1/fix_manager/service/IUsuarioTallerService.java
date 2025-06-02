package com.equipo1.fix_manager.service;

import com.equipo1.fix_manager.dto.*;
import com.equipo1.fix_manager.model.Taller;
import com.equipo1.fix_manager.model.UsuarioTaller;

public interface IUsuarioTallerService {





    Taller crearTallerParaUsuario(Long usuarioTallerId, CrearTallerDTO datos);



    void actualizarUsuario(Long id, EditarUsuarioDTO datos);

    boolean existePorEmail(String email);

    void actualizarTaller(Long usuarioId, CrearTallerDTO datos);

    AuthResponseDTO registrarUsuario(RegistroUsuarioTallerDTO datos);
    AuthResponseDTO login(LoginDTO dto);
    TallerResponseDTO obtenerOTallerDeUsuario(Long usuarioId);
}
