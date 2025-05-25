package com.equipo1.fix_manager.service;

import com.equipo1.fix_manager.dto.*;
import com.equipo1.fix_manager.model.Agenda;
import com.equipo1.fix_manager.model.Taller;
import com.equipo1.fix_manager.model.UsuarioTaller;
import com.equipo1.fix_manager.repository.ITallerRepository;
import com.equipo1.fix_manager.repository.IUsuarioTallerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioTallerService implements IUsuarioTallerService {

    @Autowired
    private IUsuarioTallerRepository usuarioTallerRepository;

    @Autowired
    private ITallerRepository tallerRepository;

    @Override
    public UsuarioTaller registrar(RegistroUsuarioTallerDTO datos) {
        UsuarioTaller nuevo = new UsuarioTaller();
        nuevo.setEmail(datos.getEmail());
        nuevo.setContrasenia(datos.getContrasenia());
        nuevo.setNombre(datos.getNombre());
        nuevo.setApellido(datos.getApellido());
        return usuarioTallerRepository.save(nuevo);
    }

    @Override
    public LoginResponseDTO login(LoginDTO datos) {
        Optional<UsuarioTaller> opt = usuarioTallerRepository.findByEmail(datos.getEmail());

        if (opt.isEmpty()) return new LoginResponseDTO(false, "Usuario no encontrado.");

        UsuarioTaller usuario = opt.get();

        if (!usuario.getContrasenia().equals(datos.getContrasenia())) {
            return new LoginResponseDTO(false, "Contraseña incorrecta.");
        }

        return new LoginResponseDTO(true, "Login exitoso.");
    }

    @Override
    public Taller crearTallerParaUsuario(Long usuarioTallerId, CrearTallerDTO datos) {
        UsuarioTaller usuario = usuarioTallerRepository.findById(usuarioTallerId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (usuario.getTaller() != null) {
            throw new IllegalStateException("El usuario ya tiene un taller registrado.");
        }

        Taller taller = new Taller();
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
                taller.getImagenLogo()
        );
    }

    @Override
    public boolean existePorEmail(String email) {
        return usuarioTallerRepository.existsByEmail(email);
    }
}
