package com.grupo6.clinicaodontologica.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name="domicilios")
public class Domicilio  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secuencia_domicilio")
    @SequenceGenerator(name = "secuencia_domicilio", sequenceName = "BD_SECUENCIA_DOMICILIO", allocationSize = 1)
    @Column
    private Integer id;

    @Column
    private String calle;
    @Column
    private String numero;
    @Column
    private String localidad;
    @Column
    private String provincia;

    @OneToMany(mappedBy = "domicilio", fetch = FetchType.LAZY)
    @JsonIgnore
    // @JsonBack...
    private Set<Paciente> pacientes = new HashSet<>();

    public Domicilio() {}

    public Domicilio(Integer id, String calle, String numero, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Domicilio{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", pacientes=" + pacientes +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Domicilio domicilio = (Domicilio) o;
        return Objects.equals(id, domicilio.id) && Objects.equals(calle, domicilio.calle) && Objects.equals(numero, domicilio.numero) && Objects.equals(localidad, domicilio.localidad) && Objects.equals(provincia, domicilio.provincia) && Objects.equals(pacientes, domicilio.pacientes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calle, numero, localidad, provincia, pacientes);
    }
}
