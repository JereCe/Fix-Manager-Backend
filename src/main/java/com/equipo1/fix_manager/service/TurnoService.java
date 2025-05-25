package com.equipo1.fix_manager.service;


import com.equipo1.fix_manager.dto.CrearTurnoDTO;
import com.equipo1.fix_manager.dto.TurnoResponseDTO;
import com.equipo1.fix_manager.model.*;
import com.equipo1.fix_manager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService  implements ITurnoService{

    @Autowired
    private ITurnoRepository turnoRepo;

    @Autowired
    private IUsuarioTallerRepository usuarioTallerRepo;


    @Autowired
    private ITallerRepository tallerRepo;

    @Autowired
    private IVehiculoRepository vehiculoRepo;


    @Autowired
    private IUsuarioClienteRepository usuarioClienteRepo;



    @Override
    public void crearTurnoDesdeTaller(Long usuarioTallerId, CrearTurnoDTO datos) {
        UsuarioTaller usuario = usuarioTallerRepo.findById(usuarioTallerId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario taller no encontrado."));

        Taller taller = usuario.getTaller();
        if (taller == null || taller.getAgenda() == null) {
            throw new IllegalStateException("El taller no tiene una agenda asociada.");
        }

        Turno turno = new Turno();
        turno.setFecha(datos.getFecha());
        turno.setHora(datos.getHora());
        turno.setDisponibilidad(DisponibilidadTurno.LIBRE);
        turno.setEstado(null); // se puede setear como Estado.PENDIENTE si lo preferís
        turno.setAgenda(taller.getAgenda());

        turnoRepo.save(turno);
    }


    @Override
    public List<TurnoResponseDTO> listarTurnosDisponiblesPorTaller(Long tallerId) {
        Taller taller = tallerRepo.findById(tallerId)
                .orElseThrow(() -> new IllegalArgumentException("Taller no encontrado"));

        Agenda agenda = taller.getAgenda();
        if (agenda == null) {
            throw new IllegalStateException("El taller no tiene agenda asociada.");
        }

        List<Turno> turnos = turnoRepo.findByAgenda_IdAndDisponibilidad(agenda.getId(), DisponibilidadTurno.LIBRE);

        return turnos.stream()
                .map(t -> new TurnoResponseDTO(t.getId(), t.getFecha(), t.getHora()))
                .toList();
    }

    @Override
    public void reservarTurno(Long turnoId, Long clienteId, Long vehiculoId) {
        Turno turno = turnoRepo.findById(turnoId)
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado"));

        if (turno.getDisponibilidad() != DisponibilidadTurno.LIBRE) {
            throw new IllegalStateException("El turno ya está reservado.");
        }

        UsuarioCliente cliente = usuarioClienteRepo.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        Vehiculo vehiculo = vehiculoRepo.findById(vehiculoId)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado"));

        turno.setCliente(cliente);
        turno.setVehiculo(vehiculo);
        turno.setDisponibilidad(DisponibilidadTurno.RESERVADO);
        turno.setEstado(Estado.PENDIENTE);

        turnoRepo.save(turno);
    }



}
