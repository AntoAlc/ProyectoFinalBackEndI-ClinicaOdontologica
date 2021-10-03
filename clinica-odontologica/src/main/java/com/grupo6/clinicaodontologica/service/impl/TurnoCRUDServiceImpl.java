package com.grupo6.clinicaodontologica.service.impl;

import com.grupo6.clinicaodontologica.dto.OdontologoDTO;
import com.grupo6.clinicaodontologica.dto.PacienteDTO;
import com.grupo6.clinicaodontologica.dto.TurnoDTO;
import com.grupo6.clinicaodontologica.persistence.model.Turno;
import com.grupo6.clinicaodontologica.persistence.repository.ITurnoRepository;
import com.grupo6.clinicaodontologica.service.ICRUDService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("turnoService")
public class TurnoCRUDServiceImpl implements ICRUDService<TurnoDTO> {

    @Autowired
    private ICRUDService<OdontologoDTO> odontologoService;
    @Autowired
    private ICRUDService<PacienteDTO> pacienteService;
    @Autowired
    private ITurnoRepository iTurnoRepository;


    public List<TurnoDTO> buscarTodos() {
        List<TurnoDTO> turnos = new ArrayList<>();

        for (Turno t : iTurnoRepository.findAll()) {
            turnos.add(new TurnoDTO(t));
        }
        return turnos;
    }

    public TurnoDTO buscarPorId(Integer id) {
        //Turno turno = ITurnoRepository.findById(id).orElse(null);

        return new TurnoDTO(iTurnoRepository.findById(id).orElse(null));
    }


    public void eliminar(Integer id) {
        iTurnoRepository.deleteById(id);
    }

    @Override
    public TurnoDTO actualizar(TurnoDTO turnoDTO) {

        Turno turno = iTurnoRepository.getById(turnoDTO.getId());

        if (turno == null) {
            return null;
        }

        if (validarFranjaHorariaOcupada(turnoDTO.getFecha()) || !(existeOdontologoYPaciente(turnoDTO.getPaciente().getId(), turnoDTO.getOdontologo().getId()))) {
            return null;
            //    throw new Exception("Datos incorrectos");
        }

        turno.setOdontologo(turnoDTO.getOdontologo().toEntity());
        turno.setPaciente(turnoDTO.getPaciente().toEntity());
        turno.setFecha(turnoDTO.getFecha());

        iTurnoRepository.save(turno);

        return turnoDTO;
    }


    private boolean existeOdontologoYPaciente(Integer pacienteId, Integer odontologoId) {
        PacienteDTO pacienteDTO = pacienteService.buscarPorId(pacienteId);
        OdontologoDTO odontologoDTO = odontologoService.buscarPorId(odontologoId);
        return (pacienteDTO != null && odontologoDTO != null);
    }


    private boolean validarFranjaHorariaOcupada(LocalDateTime fechaTurno) {

        LocalDateTime horaFinalizacionTurnoNuevo = fechaTurno.plusMinutes(59);

        for (Turno t : iTurnoRepository.findAll()) {
            LocalDateTime horaFinalizacionTurnoExistente = t.getFecha().plusMinutes(59);
            LocalDateTime fechaInicialExistente = t.getFecha();

            if (
                    (horaFinalizacionTurnoNuevo.isAfter(fechaInicialExistente) && fechaTurno.isBefore(fechaInicialExistente)) ||
                            (fechaTurno.isEqual(fechaInicialExistente)) ||
                            (fechaTurno.isAfter(fechaInicialExistente) && fechaTurno.isBefore(horaFinalizacionTurnoExistente))

            )
                return true;
        }
        return false;
    }


    public TurnoDTO crear(TurnoDTO turnoDTO) {

        if (validarFranjaHorariaOcupada(turnoDTO.getFecha()) || !(existeOdontologoYPaciente(turnoDTO.getPaciente().getId(), turnoDTO.getOdontologo().getId()))) {
            return null;
        }

        Integer pacienteId = turnoDTO.getPaciente().getId();
        Integer odontologoId = turnoDTO.getOdontologo().getId();



        turnoDTO.setPaciente(pacienteService.buscarPorId(pacienteId));
        turnoDTO.setOdontologo(odontologoService.buscarPorId(odontologoId));
        Turno turnoGuardado = iTurnoRepository.save(turnoDTO.toEntity());
        turnoDTO.setId(turnoGuardado.getId());

       // return new TurnoDTO(iTurnoRepository.save(turnoDTO.toEntity()));

        return turnoDTO;
    }


    public List<TurnoDTO> buscarTurnosProximos(LocalDate fecha, int dia) {
        List<TurnoDTO> turnos = new ArrayList<>();

        LocalDateTime fechaLimite = fecha.plusDays(dia + 1).atStartOfDay();

        LocalDateTime fechaCompleta = fecha.atStartOfDay();

        for (Turno t : iTurnoRepository.findAll()) {
            if ((t.getFecha().isAfter(fechaCompleta) || t.getFecha().equals(fecha)) && t.getFecha().isBefore(fechaLimite))
                turnos.add(new TurnoDTO(t));
        }

        return turnos;

    }


}
