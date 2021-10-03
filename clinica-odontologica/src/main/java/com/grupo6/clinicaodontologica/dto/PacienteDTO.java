package com.grupo6.clinicaodontologica.dto;

import com.grupo6.clinicaodontologica.persistence.model.Paciente;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PacienteDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private LocalDateTime fechaIngreso;
    private DomicilioDTO domicilio;


    public PacienteDTO() {
    }

    public PacienteDTO(Paciente p){
        this.id=p.getId();
        this.nombre=p.getNombre();
        this.apellido =p.getApellido();
        this.dni=p.getDni();
        this.fechaIngreso =p.getFechaIngreso();
        this.domicilio = new DomicilioDTO(p.getDomicilio());
    }


    public PacienteDTO(Integer id, String nombre, String apellido, Integer dni, LocalDateTime fechaIngreso, DomicilioDTO domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public Paciente toEntity(){
        Paciente entity = new Paciente();

        entity.setId(id);
        entity.setNombre(nombre);
        entity.setApellido(apellido);
        entity.setDni(dni);
        entity.setFechaIngreso(fechaIngreso);
        entity.setDomicilio(domicilio.toEntity());

        return entity;
    }



}