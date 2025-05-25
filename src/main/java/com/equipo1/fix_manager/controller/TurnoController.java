package com.equipo1.fix_manager.controller;

import com.equipo1.fix_manager.dto.CrearTurnoDTO;
import com.equipo1.fix_manager.dto.TurnoResponseDTO;
import com.equipo1.fix_manager.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
@CrossOrigin(origins = "*")
public class TurnoController {

    @Autowired
    private ITurnoService turnoService;


    @PostMapping("/taller/{id}/crear")
    public ResponseEntity<Void> crearTurno(@PathVariable Long id, @RequestBody CrearTurnoDTO datos) {
        turnoService.crearTurnoDesdeTaller(id, datos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/disponibles")
    public ResponseEntity<?> listarDisponiblesPorTaller(@RequestParam Long tallerId) {
        List<TurnoResponseDTO> turnos = turnoService.listarTurnosDisponiblesPorTaller(tallerId);

        if (turnos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(turnos);
    }

    @PutMapping("/{turnoId}/reservar")
    public ResponseEntity<Void> reservarTurno(
            @PathVariable Long turnoId,
            @RequestParam Long clienteId,
            @RequestParam Long vehiculoId) {

        turnoService.reservarTurno(turnoId, clienteId, vehiculoId);
        return ResponseEntity.ok().build(); // 200 OK
    }


}
