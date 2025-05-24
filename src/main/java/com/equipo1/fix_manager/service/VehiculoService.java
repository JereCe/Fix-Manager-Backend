package com.equipo1.fix_manager.service;


import com.equipo1.fix_manager.dto.VehiculoDTO;
import com.equipo1.fix_manager.dto.VehiculoResponseDTO;
import com.equipo1.fix_manager.model.UsuarioCliente;
import com.equipo1.fix_manager.model.Vehiculo;
import com.equipo1.fix_manager.repository.IUsuarioClienteRepository;
import com.equipo1.fix_manager.repository.IVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService implements IVehiculoService {

    @Autowired
    private IVehiculoRepository vehiculoRepo;

    @Autowired
    private IUsuarioClienteRepository usuarioClienteRepo;

    @Override
    public Vehiculo crearVehiculo(VehiculoDTO datos) {
        UsuarioCliente usuario = usuarioClienteRepo.findById(datos.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMarca(datos.getMarca());
        vehiculo.setModelo(datos.getModelo());
        vehiculo.setPatente(datos.getPatente());
        vehiculo.setUsuarioCliente(usuario);

        return vehiculoRepo.save(vehiculo);
    }

    @Override
    public List<VehiculoResponseDTO> obtenerVehiculosPorUsuario(Long usuarioId) {
        List<Vehiculo> vehiculos = vehiculoRepo.findByUsuarioCliente_Id(usuarioId);

        return vehiculos.stream()
                .map(v -> new VehiculoResponseDTO(v.getId(), v.getMarca(), v.getModelo(), v.getPatente()))
                .toList();
    }



}
