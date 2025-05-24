package com.equipo1.fix_manager.controller;

import com.equipo1.fix_manager.dto.MensajeRespuestaDTO;
import com.equipo1.fix_manager.dto.VehiculoDTO;
import com.equipo1.fix_manager.dto.VehiculoResponseDTO;
import com.equipo1.fix_manager.model.Vehiculo;
import com.equipo1.fix_manager.service.IVehiculoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
@CrossOrigin(origins = "*")
public class VehiculoController {

    @Autowired
    private IVehiculoService vehiculoService;

    @PostMapping
    public ResponseEntity<MensajeRespuestaDTO> crearVehiculo(@RequestBody VehiculoDTO datos) {
        vehiculoService.crearVehiculo(datos);
        return ResponseEntity.ok(new MensajeRespuestaDTO("Vehículo creado correctamente."));
    }

    @GetMapping("/usuario/{id}")
    public List<VehiculoResponseDTO> obtenerVehiculosDeUsuario(@PathVariable Long id) {
        return vehiculoService.obtenerVehiculosPorUsuario(id);
    }
}
