package com.equipo1.fix_manager.controller;

import com.equipo1.fix_manager.dto.CrearTallerDTO;
import com.equipo1.fix_manager.dto.TallerDTO;
import com.equipo1.fix_manager.dto.TallerResponseDTO;
import com.equipo1.fix_manager.model.Taller;
import com.equipo1.fix_manager.model.TipoReparacion;
import com.equipo1.fix_manager.model.UsuarioTaller;
import com.equipo1.fix_manager.service.TallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/talleres")
@CrossOrigin(origins = "*")
public class TallerController {

    @Autowired
    private TallerService tallerService;







    @GetMapping("/todos")
    public ResponseEntity<List<TallerResponseDTO>> obtenerTodosLosTalleres() {
        List<TallerResponseDTO> talleres = tallerService.obtenerTodos();
        return ResponseEntity.ok(talleres);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<TallerResponseDTO>> filtrarTalleres(
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) TipoReparacion tipo) {
        List<TallerResponseDTO> talleresFiltrados = tallerService.filtrarConCalificacion(ciudad, tipo);
        return ResponseEntity.ok(talleresFiltrados);
    }

    @PutMapping("/{id}/tipos-reparacion")
    public ResponseEntity<Void> actualizarTiposReparacion(
            @PathVariable Long id,
            @RequestBody List<String> tipos) {
        tallerService.actualizarTiposReparacion(id, tipos);
        return ResponseEntity.ok().build();
    }
}
