package com.equipo1.fix_manager.service;

import com.equipo1.fix_manager.dto.LoginDTO;
import com.equipo1.fix_manager.dto.LoginResponseDTO;
import com.equipo1.fix_manager.dto.RegistroUsuarioClienteDTO;
import com.equipo1.fix_manager.model.UsuarioCliente;

public interface IUsuarioClienteService {

    public UsuarioCliente registrarUsuario(RegistroUsuarioClienteDTO datos);

    LoginResponseDTO login(LoginDTO datos);

}
