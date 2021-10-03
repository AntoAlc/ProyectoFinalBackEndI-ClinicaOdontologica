package com.grupo6.clinicaodontologica.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name= "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secuencia_paciente")
    @SequenceGenerator(name = "secuencia_paciente", sequenceName = "BD_SECUENCIA_PACIENTE", allocationSize = 1)
    @Column
    private Integer id;

    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private Integer dni;
    @Column
    private LocalDateTime fechaIngreso;

    // OneToOne
    // ManyToOne
    // OneToMany
    // ManyToMany
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", nullable = false)
    private Domicilio domicilio;

    public Paciente() {}

    public Paciente(Integer id, String nombre, String apellido, Integer dni, LocalDateTime fechaIngreso, Domicilio domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }


    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", domicilio=" + domicilio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(id, paciente.id) && Objects.equals(nombre, paciente.nombre) && Objects.equals(apellido, paciente.apellido) && Objects.equals(dni, paciente.dni) && Objects.equals(fechaIngreso, paciente.fechaIngreso) && Objects.equals(domicilio, paciente.domicilio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, dni, fechaIngreso, domicilio);
    }



    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Set<Turno> turnos = new HashSet<>();

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }


}

