package com.example.University.Management.System.repository;

import java.util.List;

public interface AbstractRepository<T> {
    List<T> findAll();
    T findById(String id);
    T save(T entity);           // returnează obiectul salvat
    void delete(T entity);
    void deleteById(String id); // ștergere direct după id
}
