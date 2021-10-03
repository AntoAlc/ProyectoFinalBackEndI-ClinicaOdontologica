package com.grupo6.clinicaodontologica.service.impl;

import com.grupo6.clinicaodontologica.dto.DomicilioDTO;
import com.grupo6.clinicaodontologica.persistence.model.Domicilio;
import com.grupo6.clinicaodontologica.persistence.repository.IDomicilioRepository;
import com.grupo6.clinicaodontologica.service.ICRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomicilioICRUDServiceImpl implements ICRUDService<DomicilioDTO> {


    private final IDomicilioRepository IDomicilioRepository;

    @Autowired
    public DomicilioICRUDServiceImpl(IDomicilioRepository IDomicilioRepository) {
        this.IDomicilioRepository = IDomicilioRepository;
    }


     @Override
    public DomicilioDTO buscarPorId(Integer id) {
        return new DomicilioDTO(IDomicilioRepository.getById(id));
    }

    @Override
    public DomicilioDTO crear(DomicilioDTO domicilioDTO) {
        return new DomicilioDTO(IDomicilioRepository.save(domicilioDTO.toEntity()));
    }

    @Override
    public List<DomicilioDTO> buscarTodos() {
        List<DomicilioDTO> domicilios = new ArrayList<>();

        for (Domicilio d : IDomicilioRepository.findAll()) {
            domicilios.add(new DomicilioDTO(d));
        }
        return domicilios;
    }

    @Override
    public DomicilioDTO actualizar(DomicilioDTO domicilioDTO) {
        Domicilio actualizado = null;
        if (IDomicilioRepository.findById(domicilioDTO.getId()).isPresent()) {
            actualizado = IDomicilioRepository.save(domicilioDTO.toEntity());
        }
        return new DomicilioDTO(actualizado);
    }

    @Override
    public void eliminar(Integer id) {
        IDomicilioRepository.deleteById(id);
    }
}
