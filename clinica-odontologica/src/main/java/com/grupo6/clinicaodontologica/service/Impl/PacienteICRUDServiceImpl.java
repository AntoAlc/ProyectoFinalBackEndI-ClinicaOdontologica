package com.grupo6.clinicaodontologica.service.Impl;

import com.grupo6.clinicaodontologica.dto.PacienteDTO;
import com.grupo6.clinicaodontologica.persistence.model.Paciente;
import com.grupo6.clinicaodontologica.persistence.repository.IPacienteRepository;
import com.grupo6.clinicaodontologica.service.ICRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("pacienteService")
public class PacienteICRUDServiceImpl implements ICRUDService<PacienteDTO> {

    private IPacienteRepository IPacienteRepository;

    @Autowired
    public PacienteICRUDServiceImpl(IPacienteRepository IPacienteRepository) {
        this.IPacienteRepository = IPacienteRepository;
    }


    @Override
    public List<PacienteDTO> buscarTodos() {
        List<PacienteDTO> pacientes = new ArrayList<>();

        for (Paciente p : IPacienteRepository.findAll()) {
            pacientes.add(new PacienteDTO(p));
        }
        return pacientes;
    }

    @Override
    public PacienteDTO buscarPorId(Integer id) {
        return new PacienteDTO(IPacienteRepository.findById(id).orElse(null));
    }

    @Override
    public PacienteDTO crear(PacienteDTO pacienteDTO) {
        pacienteDTO.setFechaIngreso(LocalDateTime.now());
        return new PacienteDTO(IPacienteRepository.save(pacienteDTO.toEntity()));

    }

    @Override
    public PacienteDTO actualizar(PacienteDTO pacienteDTO) {
        Paciente actualizado = null;
        if (IPacienteRepository.findById(pacienteDTO.getId()).isPresent()) {
            actualizado = IPacienteRepository.save(pacienteDTO.toEntity());
        }
        return new PacienteDTO(actualizado);
    }

    @Override
    public void eliminar(Integer id) {
        IPacienteRepository.deleteById(id);
    }

}
