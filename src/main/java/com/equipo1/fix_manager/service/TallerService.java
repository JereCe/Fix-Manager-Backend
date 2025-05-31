package com.equipo1.fix_manager.service;


import com.equipo1.fix_manager.dto.TallerResponseDTO;
import com.equipo1.fix_manager.model.Taller;
import com.equipo1.fix_manager.repository.ITallerRepository;
import com.equipo1.fix_manager.repository.ITurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TallerService {

    @Autowired
    private ITallerRepository tallerRepo;

    @Autowired
    private ITurnoRepository turnoRepo;





}
