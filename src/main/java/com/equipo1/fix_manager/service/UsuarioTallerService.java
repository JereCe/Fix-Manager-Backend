package com.equipo1.fix_manager.service;

import com.equipo1.fix_manager.dto.*;
import com.equipo1.fix_manager.exception.UnauthorizedException;
import com.equipo1.fix_manager.model.Agenda;
import com.equipo1.fix_manager.model.Taller;
import com.equipo1.fix_manager.model.UsuarioTaller;
import com.equipo1.fix_manager.repository.ITallerRepository;
import com.equipo1.fix_manager.repository.IUsuarioTallerRepository;
import com.equipo1.fix_manager.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioTallerService implements IUsuarioTallerService {

    @Autowired
    private IUsuarioTallerRepository usuarioTallerRepository;

    @Autowired
    private ITallerRepository tallerRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDTO registrarUsuario(RegistroUsuarioTallerDTO datos) {
        if (usuarioTallerRepository.existsByEmail(datos.getEmail())) {
            throw new RuntimeException("El email ya está registrado.");
        }

        UsuarioTaller nuevo = new UsuarioTaller();
        nuevo.setNombre(datos.getNombre());
        nuevo.setApellido(datos.getApellido());
        nuevo.setEmail(datos.getEmail());
        nuevo.setContrasenia(passwordEncoder.encode(datos.getContrasenia()));

        usuarioTallerRepository.save(nuevo);

        String token = jwtService.generateToken(nuevo.getEmail());

        return new AuthResponseDTO(
                token,
                "TALLER",
                nuevo.getId(),
                nuevo.getEmail(),
                nuevo.getNombre()
        );
    }


    @Override
    public AuthResponseDTO login(LoginDTO dto) {
        UsuarioTaller cliente = usuarioTallerRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email no encontrado"));

        if (!passwordEncoder.matches(dto.getContrasenia(), cliente.getContrasenia())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }

        String token = jwtService.generateToken(cliente.getEmail());
        return new AuthResponseDTO(
                token,
                "TALLER",
                cliente.getId(),
                cliente.getEmail(),
                cliente.getNombre()
        );
    }



    @Override
    public Taller crearTallerParaUsuario(Long usuarioTallerId, CrearTallerDTO datos) {
        UsuarioTaller usuario = usuarioTallerRepository.findById(usuarioTallerId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (usuario.getTaller() != null) {
            throw new IllegalStateException("El usuario ya tiene un taller registrado.");
        }

        Taller taller = new Taller();
        taller.setNombre(datos.getNombre());
        taller.setDescripcion(datos.getDescripcion());
        taller.setUbicacion(datos.getUbicacion());
        taller.setImagenLogo(datos.getImagenLogo());

        Agenda agenda = new Agenda();
        agenda.setTaller(taller);
        taller.setAgenda(agenda);

        Taller guardado = tallerRepository.save(taller);
        usuario.setTaller(guardado);
        usuarioTallerRepository.save(usuario);

        return guardado;
    }

    @Override
    public TallerResponseDTO obtenerOTallerDeUsuario(Long usuarioId) {
        UsuarioTaller usuario = usuarioTallerRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Taller taller = usuario.getTaller();

        if (taller == null) return null;

        return new TallerResponseDTO(
                taller.getId(),
                taller.getDescripcion(),
                taller.getUbicacion(),
                taller.getImagenLogo(),
                taller.getPromedioCalificacion(),
                taller.getCantidadCalificaciones()
        );
    }


    @Override
    public boolean existePorEmail(String email) {
        return usuarioTallerRepository.existsByEmail(email);
    }

    @Override
    public void actualizarUsuario(Long id, EditarUsuarioDTO datos) {
        UsuarioTaller usuario = usuarioTallerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario taller no encontrado."));

        usuario.setNombre(datos.getNombre());
        usuario.setApellido(datos.getApellido());
        usuario.setContrasenia(datos.getContrasenia());

        usuarioTallerRepository.save(usuario);
    }

    @Override
    public void actualizarTaller(Long usuarioId, CrearTallerDTO datos) {
        UsuarioTaller usuario = usuarioTallerRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Taller taller = usuario.getTaller();

        if (taller == null) {
            throw new IllegalStateException("El usuario aún no tiene un taller registrado.");
        }

        taller.setDescripcion(datos.getDescripcion());
        taller.setUbicacion(datos.getUbicacion());
        taller.setImagenLogo(datos.getImagenLogo());

        tallerRepository.save(taller);
    }


}
