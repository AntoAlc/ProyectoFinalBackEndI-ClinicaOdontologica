package com.grupo6.clinicaodontologica.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICRUDService<T> {
    List<T> buscarTodos();
    T buscarPorId(Integer id);
    T crear(T t);
    T actualizar(T t);
    void eliminar(Integer id);
}
