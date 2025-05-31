package com.equipo1.fix_manager.controller;

import com.equipo1.fix_manager.dto.*;

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
    public ResponseEntity<Void> registrar(@RequestBody RegistroUsuarioTallerDTO datos) {
        if (usuarioTallerService.existePorEmail(datos.getEmail())) {
            throw new IllegalStateException("El email ya está registrado.");
        }

        usuarioTallerService.registrar(datos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO datos) {
        LoginResponseDTO respuesta = usuarioTallerService.login(datos);
        return ResponseEntity.ok(respuesta); // 200 OK
    }
    @PostMapping("/{id}/crear-taller")
    public ResponseEntity<Void> crearTaller(@PathVariable Long id, @RequestBody CrearTallerDTO datos) {
        usuarioTallerService.crearTallerParaUsuario(id, datos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/{id}/taller")
    public ResponseEntity<?> obtenerTaller(@PathVariable Long id) {
        TallerResponseDTO dto = usuarioTallerService.obtenerOTallerDeUsuario(id);

        if (dto == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Void> actualizarUsuTaller(@PathVariable Long id, @RequestBody EditarUsuarioDTO datos) {
        usuarioTallerService.actualizarUsuario(id, datos);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/modificar")
    public ResponseEntity<Void> actualizarTaller(@PathVariable Long id, @RequestBody CrearTallerDTO datos) {
        usuarioTallerService.actualizarTaller(id, datos);
        return ResponseEntity.ok().build(); // 200 OK
    }

}
