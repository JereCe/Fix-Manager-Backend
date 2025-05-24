package com.equipo1.fix_manager.repository;

import com.equipo1.fix_manager.model.UsuarioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioClienteRepository extends JpaRepository<UsuarioCliente,Long> {
}
