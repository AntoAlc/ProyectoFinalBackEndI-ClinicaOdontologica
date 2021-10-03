package com.grupo6.clinicaodontologica.service.Impl;

import com.grupo6.clinicaodontologica.dto.OdontologoDTO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


@FixMethodOrder(MethodSorters.DEFAULT)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoICRUDServiceImplTest {

    @Autowired
    private OdontologoICRUDServiceImpl odontologoICRUDService;
    private OdontologoDTO odontologoDTO;


    public void initialSetting() throws Exception{
        this.odontologoICRUDService.crear(new OdontologoDTO(1,"Alejandro", "March", 23413123));
    }


    @Test
    public void TestN1DebeGuardarOdontologo() throws Exception{
        OdontologoDTO odontologo1 = new OdontologoDTO(2, "Maria","Perez",12312313);
        OdontologoDTO odontologo = odontologoICRUDService.crear(odontologo1);
        Assertions.assertTrue(odontologo.getId() != null);
    }

    @Test
    public void TestN2DebeMostrarTodosLosOdontologos() throws Exception{
        //Dado
        initialSetting();
        List<OdontologoDTO> listaOdontologos = new ArrayList<>();
        Integer respuestaEsperada = 1;
        //Cuando
        Integer respuestaObtenida = odontologoICRUDService.buscarTodos().size();
        //Entonces
        Assertions.assertEquals(respuestaEsperada, respuestaObtenida);
    }


    @Test
    @Transactional
    public void TestN3DebeBorrarOdontologoPorId() throws Exception{
        initialSetting();
        List<OdontologoDTO> listaOdontologos = new ArrayList<>();
        odontologoICRUDService.eliminar(1);
        Integer respuestaEsperada = 0;
        //Cuando
        Integer respuestaObtenida = odontologoICRUDService.buscarTodos().size();
        //Entonces
        Assertions.assertEquals(respuestaEsperada, respuestaObtenida);
    }


}