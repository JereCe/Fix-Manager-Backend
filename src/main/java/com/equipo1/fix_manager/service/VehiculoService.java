package com.equipo1.fix_manager.service;


import com.equipo1.fix_manager.dto.VehiculoDTO;
import com.equipo1.fix_manager.dto.VehiculoResponseDTO;
import com.equipo1.fix_manager.model.Historial;
import com.equipo1.fix_manager.model.UsuarioCliente;
import com.equipo1.fix_manager.model.Vehiculo;
import com.equipo1.fix_manager.repository.IHistorialRepository;
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

    @Autowired
    private IHistorialRepository historialRepo;

    @Override
    public Vehiculo crearVehiculo(VehiculoDTO datos) {
        UsuarioCliente cliente = usuarioClienteRepo.findById(datos.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario cliente no encontrado."));

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMarca(datos.getMarca());
        vehiculo.setModelo(datos.getModelo());
        vehiculo.setPatente(datos.getPatente());
        vehiculo.setUsuarioCliente(cliente);

        Historial historial = new Historial();
        historial.setVehiculo(vehiculo);
        historialRepo.save(historial);

        vehiculo.setHistorial(historial);

        return vehiculoRepo.save(vehiculo);
    }

    @Override
    public List<VehiculoResponseDTO> obtenerVehiculosPorUsuario(Long usuarioId) {
        List<Vehiculo> lista = vehiculoRepo.findByUsuarioCliente_Id(usuarioId);

        return lista.stream()
                .map(v -> new VehiculoResponseDTO(v.getId(), v.getMarca(), v.getModelo(), v.getPatente()))
                .toList();
    }

    @Override
    public void actualizarVehiculo(Long id, VehiculoDTO datos) {
        Vehiculo vehiculo = vehiculoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado."));

        vehiculo.setMarca(datos.getMarca());
        vehiculo.setModelo(datos.getModelo());
        vehiculo.setPatente(datos.getPatente());

        vehiculoRepo.save(vehiculo);
    }

    @Override
    public void eliminarVehiculo(Long id) {
        Vehiculo vehiculo = vehiculoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado."));

        vehiculoRepo.delete(vehiculo);
    }




}
