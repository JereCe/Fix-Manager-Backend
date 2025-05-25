package com.equipo1.fix_manager.service;

import com.equipo1.fix_manager.dto.CrearTurnoDTO;
import com.equipo1.fix_manager.dto.TurnoResponseDTO;

import java.util.List;

public interface ITurnoService {

    void crearTurnoDesdeTaller(Long usuarioTallerId, CrearTurnoDTO datos);

    List<TurnoResponseDTO> listarTurnosDisponiblesPorTaller(Long tallerId);


    void reservarTurno(Long turnoId, Long clienteId, Long vehiculoId);
}
