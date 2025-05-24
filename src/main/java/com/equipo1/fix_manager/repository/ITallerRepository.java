package com.equipo1.fix_manager.repository;


import com.equipo1.fix_manager.model.Taller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITallerRepository extends JpaRepository<Taller,Long> {
}
