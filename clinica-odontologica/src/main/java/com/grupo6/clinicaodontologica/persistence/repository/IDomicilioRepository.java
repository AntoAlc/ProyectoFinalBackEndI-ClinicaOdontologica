package com.grupo6.clinicaodontologica.persistence.repository;

import com.grupo6.clinicaodontologica.persistence.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDomicilioRepository extends JpaRepository<Domicilio, Integer> {
}
