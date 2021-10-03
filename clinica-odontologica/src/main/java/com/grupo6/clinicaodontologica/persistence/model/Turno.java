package com.grupo6.clinicaodontologica.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "turnos")
public class Turno {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="turno_seq")
    @SequenceGenerator(name = "turno_seq", sequenceName = "turno_seq", allocationSize = 1)
    @Column(name = "turno_id")
    private Integer id;

    @Setter
    @Column
    private LocalDateTime fecha;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "odontologo_id", nullable = false)
    private Odontologo odontologo;
}