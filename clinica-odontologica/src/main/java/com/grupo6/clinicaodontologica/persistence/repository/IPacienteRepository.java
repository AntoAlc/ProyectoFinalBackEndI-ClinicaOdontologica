package com.grupo6.clinicaodontologica.persistence.repository;

import com.grupo6.clinicaodontologica.persistence.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
}
