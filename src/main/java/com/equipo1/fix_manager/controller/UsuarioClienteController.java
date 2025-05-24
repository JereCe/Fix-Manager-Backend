package com.equipo1.fix_manager.controller;

import com.equipo1.fix_manager.dto.LoginDTO;
import com.equipo1.fix_manager.dto.LoginResponseDTO;
import com.equipo1.fix_manager.dto.RegistroUsuarioClienteDTO;
import com.equipo1.fix_manager.model.UsuarioCliente;
import com.equipo1.fix_manager.service.IUsuarioClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class UsuarioClienteController {

    @Autowired
    private IUsuarioClienteService usuarioService;

    @PostMapping("/registro")
    public UsuarioCliente registrarUsuario(@RequestBody RegistroUsuarioClienteDTO datos) {
        return usuarioService.registrarUsuario(datos);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO datos) {
        return usuarioService.login(datos);
    }
}
