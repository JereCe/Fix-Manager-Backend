package com.equipo1.fix_manager.service;

import com.equipo1.fix_manager.dto.*;
import com.equipo1.fix_manager.model.Taller;
import com.equipo1.fix_manager.model.UsuarioTaller;

public interface IUsuarioTallerService {

    UsuarioTaller registrar(RegistroUsuarioTallerDTO datos);

    LoginResponseDTO login(LoginDTO datos);

    Taller crearTallerParaUsuario(Long usuarioTallerId, CrearTallerDTO datos);

    TallerResponseDTO obtenerOTallerDeUsuario(Long usuarioTallerId);

    boolean existePorEmail(String email);
}
