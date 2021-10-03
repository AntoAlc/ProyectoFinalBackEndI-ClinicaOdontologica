package com.grupo6.clinicaodontologica.persistence.repository;

import com.grupo6.clinicaodontologica.persistence.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITurnoRepository extends JpaRepository<Turno, Integer> {
}
