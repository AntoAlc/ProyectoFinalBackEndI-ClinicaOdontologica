package com.grupo6.clinicaodontologica.controller;

import com.grupo6.clinicaodontologica.dto.OdontologoDTO;
import com.grupo6.clinicaodontologica.service.ICRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.apache.log4j.Logger;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    final static Logger log = Logger.getLogger(OdontologoController.class);

    @Autowired
    @Qualifier ("odontologoService")
    private ICRUDService<OdontologoDTO> odontologoService;

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<OdontologoDTO>> buscarTodos() {
        log.debug("---- Buscando todos los odontólogos----");
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<OdontologoDTO> buscarPorId(@PathVariable Integer id) {
        log.debug("---- Buscando odontólogo por id.... ----");
        ResponseEntity<OdontologoDTO> respuesta = ResponseEntity.notFound().build();
        OdontologoDTO odontologoDTO = odontologoService.buscarPorId(id);

        if (odontologoService.buscarPorId(id) != null) {
            respuesta = ResponseEntity.ok(odontologoService.buscarPorId(id));
            log.info("Odontólogo con id: " + odontologoDTO.getId() + " encontrado.");
        } else {
            log.error("No se encontró odontólogo con id: " + odontologoDTO.getId());
        }
        return respuesta;
    }

    @PostMapping("/crear")
    public ResponseEntity<OdontologoDTO> crear(@RequestBody OdontologoDTO odontologoDTO) throws Exception {
        log.debug("Se está ejecutando el método 'crear' en odontólogo");
        ResponseEntity<OdontologoDTO> respuesta = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        if (odontologoDTO != null) {
            log.debug("Registrando odontólogo con id: " + odontologoDTO.getId());
            respuesta = ResponseEntity.ok(odontologoService.crear(odontologoDTO));
        }else{
            log.error("No se encontró odontólogo con id: " + odontologoDTO.getId());
        }
        return respuesta;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDTO> actualizar(@RequestBody OdontologoDTO odontologoDTO) throws Exception {
        log.debug("Se está ejecutando el método 'actualizar' en odontólogo");
        ResponseEntity<OdontologoDTO> respuesta = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        if (odontologoDTO != null) {
            OdontologoDTO actualizado = odontologoService.actualizar(odontologoDTO);
            if (actualizado != null) {
                log.info("Odontólogo con id: " + odontologoDTO.getId() + " actualizado.");
                respuesta = ResponseEntity.ok(actualizado);
            } else {
                log.error("No se encontró odontólogo con id: " + odontologoDTO.getId());
                respuesta = ResponseEntity.badRequest().build();
            }
        }
        return respuesta;
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> respuesta = ResponseEntity.notFound().build();
        OdontologoDTO odontologoDTO = odontologoService.buscarPorId(id);
        if (odontologoDTO != null) {
            odontologoService.eliminar(id);
            respuesta = ResponseEntity.ok("Se ha eliminado el odontólogo con id " + id);
            log.info("Odontólogo con id: " + odontologoDTO.getId() + " eliminado.");
        }else{
            log.error("No se encontró odontólogo con id: " + odontologoDTO.getId());
        }
        return respuesta;
    }


}
