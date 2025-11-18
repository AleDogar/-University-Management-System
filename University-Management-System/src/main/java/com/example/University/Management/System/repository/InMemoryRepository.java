package com.example.University.Management.System.repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class InMemoryRepository<T> implements AbstractRepository<T> {

    private final Map<String, T> storage = new LinkedHashMap<>();
    private final AtomicInteger idGen = new AtomicInteger(1);

    protected abstract String getId(T entity);
    protected abstract void setId(T entity, String id);

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }

    @Override
    public T save(T entity) {
        String id = getId(entity);
        if (id == null || id.isBlank()) {
            id = String.valueOf(idGen.getAndIncrement());
            setId(entity, id);
        }
        storage.put(id, entity);
        return entity;
    }

    @Override
    public void delete(T entity) {
        String id = getId(entity);
        if (id != null) {
            storage.remove(id);
        }
    }

    @Override
    public void deleteById(String id) {
        storage.remove(id);
    }
}
