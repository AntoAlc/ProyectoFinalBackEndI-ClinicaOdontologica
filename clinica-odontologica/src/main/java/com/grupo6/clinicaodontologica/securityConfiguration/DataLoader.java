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
        String hashedPassword = passwordEncoder.encode("AntoAlc");
        iUserRepository.save(new User("Antonella", "AntoAlc", "anto@dh.com", hashedPassword, UserRoles.ADMIN));
    }
}