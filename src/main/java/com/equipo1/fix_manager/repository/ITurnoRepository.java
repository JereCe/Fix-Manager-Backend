package com.equipo1.fix_manager.repository;


import com.equipo1.fix_manager.model.DisponibilidadTurno;
import com.equipo1.fix_manager.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno,Long> {


    List<Turno> findByAgenda_IdAndDisponibilidad(Long agendaId, DisponibilidadTurno disponibilidad);
}
