package com.grupo6.clinicaodontologica.persistence.repository;

import com.grupo6.clinicaodontologica.persistence.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer> {
}
