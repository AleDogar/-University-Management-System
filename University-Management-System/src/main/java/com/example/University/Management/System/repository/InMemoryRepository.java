package com.example.University.Management.System.repository;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryRepository<T> implements AbstractRepository<T> {

    private final Map<String, T> storage = new HashMap<>();
    protected String getId(T entity) {
        return Integer.toString(entity.hashCode());
    }

    protected void setId(T entity, String id) {
        // implicit nu face nimic; repo-urile specifice pot suprascrie
    }

    @Override
    public void save(T entity) {
        String id = getId(entity);
        if (id == null || id.isEmpty()) {
            id = UUID.randomUUID().toString();
            setId(entity, id);
        }
        storage.put(id, entity);
    }

    @Override
    public void delete(T entity) {
        storage.remove(getId(entity));
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }
}
