package com.grupo6.clinicaodontologica.dto;

import com.grupo6.clinicaodontologica.persistence.model.Domicilio;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DomicilioDTO implements Serializable {
    private Integer id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    public DomicilioDTO() {
    }

    public DomicilioDTO(Domicilio d) {
        this.id = d.getId();
        this.calle=d.getCalle();
        this.numero=d.getNumero();
        this.localidad=d.getLocalidad();
        this.provincia=d.getProvincia();
    }

    public DomicilioDTO(String calle, String numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }


    public DomicilioDTO(Integer id, String calle, String numero, String localidad, String provincia) {
       this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }


    public Domicilio toEntity(){
        Domicilio entity = new Domicilio();

        entity.setId(id);
        entity.setCalle(calle);
        entity.setNumero(numero);
        entity.setLocalidad(localidad);
        entity.setProvincia(provincia);

        return entity;
    }

}
