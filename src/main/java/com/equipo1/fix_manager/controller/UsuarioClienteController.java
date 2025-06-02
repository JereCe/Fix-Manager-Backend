package com.equipo1.fix_manager.controller;

import com.equipo1.fix_manager.dto.*;
import com.equipo1.fix_manager.model.UsuarioCliente;
import com.equipo1.fix_manager.service.IUsuarioClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class UsuarioClienteController {

    @Autowired
    private IUsuarioClienteService usuarioService;


    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody RegistroUsuarioClienteDTO datos) {
        usuarioService.registrarUsuario(datos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO datos) {
        AuthResponseDTO response = usuarioService.login(datos);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Void> actualizarCliente(@PathVariable Long id, @RequestBody EditarUsuarioDTO datos) {
        usuarioService.actualizarUsuario(id, datos);
        return ResponseEntity.ok().build(); // 200 OK
    }
}
