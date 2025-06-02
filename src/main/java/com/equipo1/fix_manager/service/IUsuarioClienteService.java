package com.equipo1.fix_manager.service;

import com.equipo1.fix_manager.dto.*;
import com.equipo1.fix_manager.model.UsuarioCliente;

public interface IUsuarioClienteService {



    AuthResponseDTO registrarUsuario(RegistroUsuarioClienteDTO datos);
    AuthResponseDTO login(LoginDTO dto);

    boolean existePorEmail(String email);

    void actualizarUsuario(Long id, EditarUsuarioDTO datos);

}
