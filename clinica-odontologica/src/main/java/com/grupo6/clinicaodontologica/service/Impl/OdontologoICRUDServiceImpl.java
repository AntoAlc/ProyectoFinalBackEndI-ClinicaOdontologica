package com.grupo6.clinicaodontologica.service.Impl;

import com.grupo6.clinicaodontologica.dto.OdontologoDTO;
import com.grupo6.clinicaodontologica.persistence.model.Odontologo;
import com.grupo6.clinicaodontologica.persistence.repository.IOdontologoRepository;
import com.grupo6.clinicaodontologica.service.ICRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("odontologoService")
public class OdontologoICRUDServiceImpl implements ICRUDService<OdontologoDTO> {

    @Autowired
    private IOdontologoRepository iOdontologoRepository;

   // public OdontologoICRUDServiceImpl(IOdontologoRepository iOdontologoRepository) {
  //    this.iOdontologoRepository = iOdontologoRepository;
  //  }


    @Override
    public List<OdontologoDTO> buscarTodos() {
        List<OdontologoDTO> odontologos = new ArrayList<>();

        for(Odontologo o : iOdontologoRepository.findAll()){
            odontologos.add(new OdontologoDTO(o));
        }
        return odontologos;
    }

    @Override
    public OdontologoDTO buscarPorId(Integer id) {
        return new OdontologoDTO(iOdontologoRepository.findById(id).orElse(null));
    }

    @Override
    public OdontologoDTO crear(OdontologoDTO odontologoDTO) {
        return new OdontologoDTO(iOdontologoRepository.save(odontologoDTO.toEntity()));

    }

    @Override
    public OdontologoDTO actualizar(OdontologoDTO odontologoDTO) {
        Odontologo actualizado = null;
        if (iOdontologoRepository.findById(odontologoDTO.getId()).isPresent()) {
            actualizado = iOdontologoRepository.save(odontologoDTO.toEntity());
        }
        return new OdontologoDTO(actualizado);
    }


    @Override
    public void eliminar(Integer id) {
        iOdontologoRepository.deleteById(id);
    }

}
