package com.equipo1.fix_manager.service;


import com.equipo1.fix_manager.dto.EditarUsuarioDTO;
import com.equipo1.fix_manager.dto.LoginDTO;
import com.equipo1.fix_manager.dto.LoginResponseDTO;
import com.equipo1.fix_manager.dto.RegistroUsuarioClienteDTO;
import com.equipo1.fix_manager.exception.UnauthorizedException;
import com.equipo1.fix_manager.model.UsuarioCliente;
import com.equipo1.fix_manager.repository.IUsuarioClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioClienteService implements IUsuarioClienteService {

    @Autowired
    private IUsuarioClienteRepository usuarioClienteRepo;



    @Override
    public UsuarioCliente registrarUsuario(RegistroUsuarioClienteDTO datos) {
        if (usuarioClienteRepo.existsByEmail(datos.getEmail())) {
            throw new RuntimeException("El email ya está registrado.");
        }

        UsuarioCliente nuevo = new UsuarioCliente();
        nuevo.setNombre(datos.getNombre());
        nuevo.setApellido(datos.getApellido());
        nuevo.setDocumento(datos.getDocumento());
        nuevo.setEmail(datos.getEmail());
        nuevo.setContrasenia(datos.getContrasenia());
        return usuarioClienteRepo.save(nuevo);
    }


    @Override
    public LoginResponseDTO login(LoginDTO datos) {
        UsuarioCliente usuario = usuarioClienteRepo.findByEmail(datos.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        if (!usuario.getContrasenia().equals(datos.getContrasenia())) {
            throw new UnauthorizedException("Contraseña incorrecta.");

        }

        return new LoginResponseDTO(true, "Login exitoso.");
    }

    @Override
    public boolean existePorEmail(String email) {
        return usuarioClienteRepo.existsByEmail(email);
    }

    @Override
    public void actualizarUsuario(Long id, EditarUsuarioDTO datos) {
        UsuarioCliente usuario = usuarioClienteRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario cliente no encontrado."));

        usuario.setNombre(datos.getNombre());
        usuario.setApellido(datos.getApellido());
        usuario.setContrasenia(datos.getContrasenia());

        usuarioClienteRepo.save(usuario);
    }



}
