package com.grupo6.clinicaodontologica.service.impl;

import com.grupo6.clinicaodontologica.dto.DomicilioDTO;
import com.grupo6.clinicaodontologica.dto.PacienteDTO;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteICRUDServiceImplTest {


    @Autowired
    private PacienteICRUDServiceImpl pacienteICRUDService;

    public void initialSetting() throws Exception {
        DomicilioDTO domicilio = new DomicilioDTO("Privet Drive", "4", "Little Whinging", "Surrey");
        PacienteDTO paciente = pacienteICRUDService.crear(new PacienteDTO(1, "Harry", "Potter", 123123, LocalDateTime.now(), domicilio));

        DomicilioDTO domicilio1 = new DomicilioDTO("Heathgate", "s/n", "Hampstead Garden Suburn", "Hampstead");
        PacienteDTO paciente1 = pacienteICRUDService.crear(new PacienteDTO(2, "Hermione", "Granger", 112233, LocalDateTime.now(), domicilio1));
    }


    @Test
    public void TestN1DebeMostrarTodosLosPacientes() throws Exception {
        //Dado
        initialSetting();
        List<PacienteDTO> listaPacientes = new ArrayList<>();
        Integer respuestaEsperada = 2;
        //Cuando
        Integer respuestaObtenida = pacienteICRUDService.buscarTodos().size();
        //Entonces
        Assertions.assertEquals(respuestaEsperada, respuestaObtenida);
    }


    @Test
    @Transactional
    public void TestN2DebeBorrarPacientePorId() throws Exception {
        initialSetting();
        List<PacienteDTO> listaOdontologos = new ArrayList<>();
        pacienteICRUDService.eliminar(1);
        Integer respuestaEsperada = 1;
        //Cuando
        Integer respuestaObtenida = pacienteICRUDService.buscarTodos().size();
        //Entonces
        Assertions.assertEquals(respuestaEsperada, respuestaObtenida);

    }

    @Test
    public void TestN3DebeGuardarPaciente() throws Exception {
        //Dado
        initialSetting();

        DomicilioDTO domicilio2 = new DomicilioDTO();
        domicilio2.setCalle("Avenida Siempre viva");
        domicilio2.setNumero("742");
        domicilio2.setLocalidad("Springfield");
        domicilio2.setProvincia("Oregon");

        PacienteDTO paciente2 = new PacienteDTO();
        paciente2.setId(4);
        paciente2.setNombre("Homero");
        paciente2.setApellido("Simpson");
        paciente2.setDni(54321);
        paciente2.setFechaIngreso(LocalDateTime.now());
        paciente2.setDomicilio(domicilio2);

        pacienteICRUDService.crear(paciente2);
        Assertions.assertTrue(paciente2.getId() != null);
    }

}