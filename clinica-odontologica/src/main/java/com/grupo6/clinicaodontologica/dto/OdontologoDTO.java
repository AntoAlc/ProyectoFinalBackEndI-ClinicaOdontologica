package com.grupo6.clinicaodontologica.dto;

import com.grupo6.clinicaodontologica.persistence.model.Odontologo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OdontologoDTO  implements Serializable {
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer matricula;


    public OdontologoDTO(){}

    public OdontologoDTO(Odontologo o){
        this.id=o.getId();
        this.nombre=o.getNombre();
        this.apellido =o.getApellido();
        this.matricula=o.getMatricula();
    }

    public OdontologoDTO(Integer id, String nombre, String apellido, Integer matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public Odontologo toEntity(){
        Odontologo entity = new Odontologo();

        entity.setId(id);
        entity.setNombre(nombre);
        entity.setApellido(apellido);
        entity.setMatricula(matricula);

        return entity;
    }

}


