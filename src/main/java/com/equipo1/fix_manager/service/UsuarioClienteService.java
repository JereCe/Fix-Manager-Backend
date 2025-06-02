package com.equipo1.fix_manager.service;


import com.equipo1.fix_manager.dto.*;
import com.equipo1.fix_manager.exception.UnauthorizedException;
import com.equipo1.fix_manager.model.UsuarioCliente;
import com.equipo1.fix_manager.repository.IUsuarioClienteRepository;
import com.equipo1.fix_manager.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioClienteService implements IUsuarioClienteService {

    @Autowired
    private IUsuarioClienteRepository usuarioClienteRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public AuthResponseDTO registrarUsuario(RegistroUsuarioClienteDTO datos) {
        if (usuarioClienteRepo.existsByEmail(datos.getEmail())) {
            throw new RuntimeException("El email ya está registrado.");
        }

        UsuarioCliente nuevo = new UsuarioCliente();
        nuevo.setNombre(datos.getNombre());
        nuevo.setApellido(datos.getApellido());
        nuevo.setDocumento(datos.getDocumento());
        nuevo.setEmail(datos.getEmail());
        nuevo.setContrasenia(passwordEncoder.encode(datos.getContrasenia()));

        usuarioClienteRepo.save(nuevo);

        String token = jwtService.generateToken(nuevo.getEmail());

        return new AuthResponseDTO(
                token,
                "CLIENTE",
                nuevo.getId(),
                nuevo.getEmail(),
                nuevo.getNombre()
        );
    }



    @Override
    public AuthResponseDTO login(LoginDTO dto) {
        UsuarioCliente cliente = usuarioClienteRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email no encontrado"));

        if (!passwordEncoder.matches(dto.getContrasenia(), cliente.getContrasenia())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }

        String token = jwtService.generateToken(cliente.getEmail());
        return new AuthResponseDTO(
                token,
                "CLIENTE",
                cliente.getId(),
                cliente.getEmail(),
                cliente.getNombre()
        );
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
