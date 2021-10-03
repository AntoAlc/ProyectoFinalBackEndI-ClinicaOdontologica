package com.grupo6.clinicaodontologica.securityConfiguration;

import com.grupo6.clinicaodontologica.persistence.model.authentication.User;
import com.grupo6.clinicaodontologica.persistence.model.authentication.UserRoles;
import com.grupo6.clinicaodontologica.persistence.repository.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private IUserRepository iUserRepository;

    @Autowired
    public DataLoader(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("password");
        iUserRepository.save(new User("admin", "admin", "admin@dh.com", hashedPassword, UserRoles.ADMIN));
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("password2");
        iUserRepository.save(new User("Anto", "anto", "anto@dh.com", hashedPassword2, UserRoles.USER));
    }

}