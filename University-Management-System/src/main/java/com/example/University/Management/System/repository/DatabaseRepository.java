package com.example.University.Management.System.repository;
import java.util.List;
import java.util.Optional;

public interface DatabaseRepository <T>{
    List<T> findAll();
    Optional<T> findById(String id);
    T add(T entity);
    void deleteById(String id);
}