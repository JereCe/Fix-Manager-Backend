package com.equipo1.fix_manager.service;


import com.equipo1.fix_manager.dto.LoginDTO;
import com.equipo1.fix_manager.dto.RegistroUsuarioClienteDTO;
import com.equipo1.fix_manager.model.UsuarioCliente;
import com.equipo1.fix_manager.repository.IUsuarioClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UsuarioCliente login(LoginDTO datos) {
        UsuarioCliente usuario = usuarioClienteRepo.findByEmail(datos.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        if (!usuario.getContrasenia().equals(datos.getContrasenia())) {
            throw new RuntimeException("Contraseña incorrecta.");
        }

        return usuario;
    }


}
