package com.grupo6.clinicaodontologica.persistence.repository;

import com.grupo6.clinicaodontologica.persistence.model.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
