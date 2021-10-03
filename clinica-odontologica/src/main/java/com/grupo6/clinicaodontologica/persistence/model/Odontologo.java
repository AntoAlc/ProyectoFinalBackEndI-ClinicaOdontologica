package com.grupo6.clinicaodontologica.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table (name = "odontologos")
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secuencia_odontologo")
    @SequenceGenerator(name = "secuencia_odontologo", sequenceName = "BD_SECUENCIA_ODONTOLOGO", allocationSize = 1)
    @Column
    private Integer id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private Integer matricula;

    public Odontologo() {}

    public Odontologo(Integer id, String nombre, String apellido, Integer matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

}

