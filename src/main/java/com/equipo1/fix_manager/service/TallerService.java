package com.equipo1.fix_manager.service;

import com.equipo1.fix_manager.dto.CrearTallerDTO;
import com.equipo1.fix_manager.dto.TallerResponseDTO;
import com.equipo1.fix_manager.model.Agenda;
import com.equipo1.fix_manager.model.Taller;
import com.equipo1.fix_manager.model.UsuarioTaller;
import com.equipo1.fix_manager.repository.ITallerRepository;
import com.equipo1.fix_manager.repository.IUsuarioTallerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class TallerService implements ITallerService {

    @Autowired
    private ITallerRepository tallerRepository;

    @Autowired
    private IUsuarioTallerRepository usuarioTallerRepository;

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

    public Taller crearTallerConImagen(Long usuarioId, CrearTallerDTO datos, MultipartFile imagen) throws IOException {
        UsuarioTaller usuario = usuarioTallerRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (usuario.getTaller() != null) {
            throw new IllegalStateException("El usuario ya tiene un taller registrado.");
        }

        Taller taller = new Taller();
        taller.setNombre(datos.getNombre());
        taller.setDescripcion(datos.getDescripcion());
        taller.setUbicacion(datos.getUbicacion());

        if (imagen != null && !imagen.isEmpty()) {
            File directorio = new File("uploads");
            if (!directorio.exists()) {
                boolean creado = directorio.mkdirs();
                System.out.println("Directorio 'uploads' creado: " + creado);
            }

            String nombreArchivo = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
            String rutaCompleta = Paths.get(directorio.getAbsolutePath(), nombreArchivo).toString();
            imagen.transferTo(new File(rutaCompleta));
            taller.setImagenLogo("/uploads/" + nombreArchivo);
        }

        Agenda agenda = new Agenda();
        agenda.setTaller(taller);
        taller.setAgenda(agenda);

        Taller guardado = tallerRepository.save(taller);
        usuario.setTaller(guardado);
        usuarioTallerRepository.save(usuario);

        return guardado;
    }
}
