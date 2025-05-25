package com.equipo1.fix_manager.controller;

import com.equipo1.fix_manager.dto.*;
import com.equipo1.fix_manager.model.Taller;
import com.equipo1.fix_manager.model.UsuarioTaller;
import com.equipo1.fix_manager.service.IUsuarioTallerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/talleres")
@CrossOrigin(origins = "*")
public class UsuarioTallerController {

    @Autowired
    private IUsuarioTallerService usuarioTallerService;

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody RegistroUsuarioTallerDTO datos) {
        try {
            if (usuarioTallerService.existePorEmail(datos.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new MensajeRespuestaDTO("El email ya está registrado."));
            }

            usuarioTallerService.registrar(datos);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MensajeRespuestaDTO("Error al registrar usuario taller."));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO datos) {
        try {
            LoginResponseDTO respuesta = usuarioTallerService.login(datos);

            if (!respuesta.isLoginExitoso()) {
                if ("Usuario no encontrado.".equals(respuesta.getMensaje())) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);
                }
            }

            return ResponseEntity.ok(respuesta); // 200 OK

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginResponseDTO(false, "Error inesperado al iniciar sesión."));
        }
    }
    @PostMapping("/{id}/crear-taller")
    public ResponseEntity<?> crearTaller(@PathVariable Long id, @RequestBody CrearTallerDTO datos) {
        try {
            usuarioTallerService.crearTallerParaUsuario(id, datos);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensajeRespuestaDTO(e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MensajeRespuestaDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MensajeRespuestaDTO("Error inesperado al crear el taller."));
        }
    }

    @GetMapping("/{id}/taller")
    public ResponseEntity<?> obtenerTaller(@PathVariable Long id) {
        try {
            TallerResponseDTO dto = usuarioTallerService.obtenerOTallerDeUsuario(id);

            if (dto == null) {
                return ResponseEntity.noContent().build(); // 204 No Content
            }

            return ResponseEntity.ok(dto); // 200 OK con datos del DTO

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MensajeRespuestaDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MensajeRespuestaDTO("Error al consultar el taller."));
        }
    }

}
