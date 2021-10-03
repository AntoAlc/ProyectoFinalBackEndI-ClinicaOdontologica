package com.grupo6.clinicaodontologica.service.Impl;

import com.grupo6.clinicaodontologica.dto.PacienteDTO;
import com.grupo6.clinicaodontologica.persistence.model.authentication.User;
import com.grupo6.clinicaodontologica.persistence.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService{

    private final IUserRepository iUserRepository;

    @Autowired
    public UserServiceImpl(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        return iUserRepository.findByEmail(email);
    }


    public User crear(User user) {

        return iUserRepository.save(user);
    }


    public List<User> buscarTodos() {
        List <User> usuarios = new ArrayList<>();
        for( User u : iUserRepository.findAll()){
            usuarios.add(u);
        }
        return  usuarios;
    }


  public User actualizar (User user){
        return iUserRepository.save(user);
  }









}
