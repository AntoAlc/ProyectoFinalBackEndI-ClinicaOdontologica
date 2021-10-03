package com.grupo6.clinicaodontologica.service.Impl;

import com.grupo6.clinicaodontologica.dto.DomicilioDTO;
import com.grupo6.clinicaodontologica.dto.OdontologoDTO;
import com.grupo6.clinicaodontologica.dto.PacienteDTO;
import com.grupo6.clinicaodontologica.dto.TurnoDTO;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
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
public class TurnoCRUDServiceImplTest {

    @Autowired
    private PacienteICRUDServiceImpl pacienteICRUDService;

    @Autowired
    private OdontologoICRUDServiceImpl odontologoICRUDService;

    @Autowired
    private TurnoCRUDServiceImpl turnoCRUDService;

    private OdontologoDTO odontologo;
    private PacienteDTO paciente;
    private TurnoDTO turno;

    public void initialSetting() throws Exception {
        DomicilioDTO domicilio = new DomicilioDTO("Avenida Siempre viva", "742", "Springfield", "Oregon");
        PacienteDTO paciente = pacienteICRUDService.crear(new PacienteDTO(1, "Homero", "Simpson", 54321, LocalDateTime.now(), domicilio));
        OdontologoDTO odontologo = odontologoICRUDService.crear(new OdontologoDTO(1, "Julius", "Hibbert", 123123));
        this.turnoCRUDService.crear(new TurnoDTO(1, LocalDateTime.of(2021, 10, 3, 15, 30), paciente, odontologo));
    }


    @Test
    public void TestN1DebeGuardarTurno() throws Exception {
        DomicilioDTO domicilio = new DomicilioDTO("Calle Falsa", "123", "Ciudad", "Springfield");
        PacienteDTO paciente = pacienteICRUDService.crear(new PacienteDTO(2, "Marge", "Simpson", 88888888, LocalDateTime.now(), domicilio));
        OdontologoDTO odontologo = odontologoICRUDService.crear(new OdontologoDTO(2, "Maria", "Perez", 123456));

        TurnoDTO turno = turnoCRUDService.crear(new TurnoDTO(2, LocalDateTime.of(2021,10,10,16,30), paciente,odontologo));
        Assertions.assertTrue(turno.getId() != null);
    }

    @Test
    public void TestN2DebeMostrarTodosLosTurnos() throws Exception {
        //Dado
        initialSetting();
        List<TurnoDTO> listaTurnos = new ArrayList<>();
        Integer respuestaEsperada = 1;
        //Cuando
        Integer respuestaObtenida = turnoCRUDService.buscarTodos().size();
        //Entonces
        Assertions.assertEquals(respuestaEsperada, respuestaObtenida);
    }


    @Test
    @Transactional
    public void TestN3DebeBorrarTurnoPorId() throws Exception {
        initialSetting();
        List<OdontologoDTO> listaOdontologos = new ArrayList<>();
        turnoCRUDService.eliminar(1);
        Integer respuestaEsperada = 0;
        //Cuando
        Integer respuestaObtenida = turnoCRUDService.buscarTodos().size();
        //Entonces
        Assertions.assertEquals(respuestaEsperada, respuestaObtenida);
    }


}
