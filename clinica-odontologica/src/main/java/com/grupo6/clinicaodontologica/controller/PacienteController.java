package com.grupo6.clinicaodontologica.controller;

import com.grupo6.clinicaodontologica.dto.PacienteDTO;
import com.grupo6.clinicaodontologica.service.ICRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;


import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    final static Logger log = Logger.getLogger(PacienteController.class);


    @Qualifier("pacienteService")
    @Autowired
    private ICRUDService<PacienteDTO> pacienteService;


    @GetMapping("/buscarTodos")
    public ResponseEntity<List<PacienteDTO>> buscarTodos() {
        log.debug("---- Buscando todos los pacientes----");
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Integer id) {
        log.debug("---- Buscando paciente por id.... ----");
        ResponseEntity<PacienteDTO> respuesta = ResponseEntity.notFound().build();
        PacienteDTO pacienteDTO = pacienteService.buscarPorId(id);
        if (pacienteDTO != null) {
            respuesta = ResponseEntity.ok(pacienteDTO);
            log.info("Paciente con id: " + pacienteDTO.getId() + " encontrado.");
        }else{
            log.error("No se encontró odontólogo con id: " + pacienteDTO.getId());
        }
        return respuesta;
    }

    @PostMapping("/crear")
    public ResponseEntity<PacienteDTO> crear(@RequestBody PacienteDTO pacienteDTO) throws Exception {
        log.debug("Se está ejecutando el método 'crear' en paciente");
        ResponseEntity<PacienteDTO> respuesta = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        if (pacienteDTO != null) {
            log.debug("Registrando paciente con id: " + pacienteDTO.getId());
            respuesta = ResponseEntity.ok(pacienteService.crear(pacienteDTO));
        }else {
            log.error("No se encontró paciente con id: " + pacienteDTO.getId());
        }
        return respuesta;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDTO> actualizar(@RequestBody PacienteDTO pacienteDTO) throws Exception {
        log.debug("Se está ejecutando el método 'actualizar' en paciente");
        ResponseEntity<PacienteDTO> respuesta = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        if (pacienteDTO != null) {
            PacienteDTO actualizado = pacienteService.actualizar(pacienteDTO);
            if (actualizado != null) {
                log.info("Paciente con id: " + pacienteDTO.getId() + " actualizado.");
                respuesta = ResponseEntity.ok(actualizado);
            } else {
                log.error("No se encontró paciente con id: " + pacienteDTO.getId());
                respuesta = ResponseEntity.badRequest().build();
            }
        }
        return respuesta;
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> respuesta = ResponseEntity.notFound().build();
        PacienteDTO pacienteDTO = pacienteService.buscarPorId(id);

        if (pacienteDTO != null) {
            pacienteService.eliminar(id);
            respuesta = ResponseEntity.ok("Se ha eliminado el paciente con id " + id);
            log.info("paciente con id: " + pacienteDTO.getId() + " eliminado.");
        }else{
            log.error("No se encontró paciente con id: " + pacienteDTO.getId());
        }
        return respuesta;
    }


}
