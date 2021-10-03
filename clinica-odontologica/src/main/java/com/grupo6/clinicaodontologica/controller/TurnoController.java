package com.grupo6.clinicaodontologica.controller;


import com.grupo6.clinicaodontologica.dto.TurnoDTO;
import com.grupo6.clinicaodontologica.service.ICRUDService;
import com.grupo6.clinicaodontologica.service.impl.TurnoCRUDServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    final static Logger log = Logger.getLogger(TurnoController.class);


    @Autowired
    @Qualifier ("turnoService")
    private ICRUDService<TurnoDTO> turnoService;

 //   @Autowired
  //  TurnoCRUDServiceImpl serviceTurnoDTO;

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<TurnoDTO>> buscarTodos() {
        log.debug("---- Buscando todos los turnos----");
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Integer id) {
        log.debug("---- Buscando turno por id.... ----");
        ResponseEntity<TurnoDTO> respuesta = ResponseEntity.notFound().build();
        TurnoDTO turnoDTO = turnoService.buscarPorId(id);

        if (turnoService.buscarPorId(id) != null) {
            respuesta = ResponseEntity.ok(turnoService.buscarPorId(id));
            log.info("Turno con id: " + turnoDTO.getId() + " encontrado.");

        } else {
            log.error("No se encontró turno con id: " + turnoDTO.getId());
        }
        return respuesta;
    }

    @PostMapping("/crear")
    public ResponseEntity<TurnoDTO> crear(@RequestBody TurnoDTO turnoDTO    ) {

        log.debug("Se está ejecutando el método 'crear' en turno");
        ResponseEntity<TurnoDTO> respuesta = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        if (turnoDTO != null) {
            log.debug("Registrando turno con id: " + turnoDTO.getId());
            respuesta = ResponseEntity.ok(turnoService.crear(turnoDTO));
        }else {
            log.error("No se encontró turno con id: " + turnoDTO.getId());
        }
        return respuesta;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDTO> actualizar(@RequestBody TurnoDTO turnoDTO) {
        log.debug("Se está ejecutando el método 'actualizar' en turno");
        ResponseEntity<TurnoDTO> respuesta = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        if (turnoDTO != null) {
            TurnoDTO actualizado =turnoService.actualizar(turnoDTO);
            if (actualizado != null) {
                log.info("Turno con id: " + turnoDTO.getId() + " actualizado.");
                respuesta = ResponseEntity.ok(actualizado);
            } else {
                log.error("No se encontró turno con id: " + turnoDTO.getId());
                respuesta = ResponseEntity.badRequest().build();
            }
        }
        return respuesta;
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> respuesta = ResponseEntity.notFound().build();
        TurnoDTO turnoDTO = turnoService.buscarPorId(id);
        if (turnoDTO != null) {
            turnoService.eliminar(id);
            respuesta = ResponseEntity.ok("Se ha eliminado el turno con id " + id);
            log.info("Turno con id: " + turnoDTO.getId() + " eliminado.");
        }else{
            log.error("No se encontró turno con id: " + turnoDTO.getId());
        }
        return respuesta;
    }


    @GetMapping("/buscarProximos" )
    public ResponseEntity<List<TurnoDTO>> buscarProximosTurnos(@RequestParam int anio, @RequestParam int mes, @RequestParam int dia, @RequestParam int cantdias) {
        LocalDate fecha = LocalDate.of(anio, mes, dia);
        return ResponseEntity.ok(((TurnoCRUDServiceImpl)turnoService).buscarTurnosProximos(fecha, cantdias));
    }



}
