package com.equipo1.fix_manager.controller;

import com.equipo1.fix_manager.dto.LoginDTO;
import com.equipo1.fix_manager.dto.LoginResponseDTO;
import com.equipo1.fix_manager.dto.MensajeRespuestaDTO;
import com.equipo1.fix_manager.dto.RegistroUsuarioClienteDTO;
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
        try {
            if (usuarioService.existePorEmail(datos.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new MensajeRespuestaDTO("El email ya está registrado."));
            }

            usuarioService.registrarUsuario(datos);
            return ResponseEntity.status(HttpStatus.CREATED).build(); // solo código 201

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MensajeRespuestaDTO("Error al registrar usuario."));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO datos) {
        try {
            LoginResponseDTO respuesta = usuarioService.login(datos);

            if (!respuesta.isLoginExitoso()) {
                if ("Usuario no encontrado.".equals(respuesta.getMensaje())) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);
                }
            }

            return ResponseEntity.ok(respuesta); // 200 OK si loginExitoso es true

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginResponseDTO(false, "Error inesperado al iniciar sesión."));
        }
    }
}
