package com.equipo1.fix_manager.controller;

import com.equipo1.fix_manager.dto.CrearTallerDTO;
import com.equipo1.fix_manager.dto.TallerResponseDTO;
import com.equipo1.fix_manager.service.TallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/talleres")
@CrossOrigin(origins = "*")
public class TallerController {

    @Autowired
    private TallerService tallerService;





    @GetMapping("/todos")
    public ResponseEntity<List<TallerResponseDTO>> obtenerTodosLosTalleres() {
        List<TallerResponseDTO> talleres = tallerService.obtenerTodos();
        return ResponseEntity.ok(talleres);
    }
}
