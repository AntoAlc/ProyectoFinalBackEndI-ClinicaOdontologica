package com.grupo6.clinicaodontologica.service.Impl;


import com.grupo6.clinicaodontologica.dto.DomicilioDTO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class DomicilioICRUDServiceImplTest {

    @Autowired
    private DomicilioICRUDServiceImpl domicilioICRUDService;
    private DomicilioDTO domicilio;


    public void initialSetting() {
        this.domicilioICRUDService.crear(new DomicilioDTO(1,"Avenida Siempre viva", "742", "Springfield", "Oregon"));
    }

    @Test
    public void TestN1DebeGuardarDomicilio() throws Exception {

        DomicilioDTO domicilio1 = new DomicilioDTO(1,"Calle Falsa", "123", "Ciudad", "Springfield");
        DomicilioDTO domicilio = domicilioICRUDService.crear(domicilio1);
        Assertions.assertTrue(domicilio.getId()!=null);
    }


    @Test
    public void TestN2DebeMostrarTodosLosDomicilios() throws Exception {
        //Dado:
        initialSetting();
        List<DomicilioDTO> listaDomicilios = new ArrayList<>();
        Integer respuestaEsperada = 1;
        //Cuando
        Integer respuestaObtenida = domicilioICRUDService.buscarTodos().size();
        //Entonces
        Assertions.assertEquals(respuestaEsperada, respuestaObtenida);
    }



    @Test
    public void TestN3DebeBorrarDomicilioPorId() throws Exception {
        //Dado:
        initialSetting();
        List<DomicilioDTO> listaDomicilios = new ArrayList<>();
        domicilioICRUDService.eliminar(1);
        Integer respuestaEsperada = 0;
        //Cuando:
        Integer respuestaObtenida = domicilioICRUDService.buscarTodos().size();
        //Entonces:
        Assertions.assertEquals(respuestaEsperada, respuestaObtenida);
    }





}