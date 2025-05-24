package com.equipo1.fix_manager.service;

import com.equipo1.fix_manager.dto.LoginDTO;
import com.equipo1.fix_manager.dto.RegistroUsuarioClienteDTO;
import com.equipo1.fix_manager.model.UsuarioCliente;

public interface IUsuarioClienteService {

    public UsuarioCliente registrarUsuario(RegistroUsuarioClienteDTO datos);

    UsuarioCliente login(LoginDTO datos);


}
