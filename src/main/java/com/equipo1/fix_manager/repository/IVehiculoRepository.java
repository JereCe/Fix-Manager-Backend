package com.equipo1.fix_manager.repository;

import com.equipo1.fix_manager.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo,Long>{
}
