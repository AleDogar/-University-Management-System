package com.example.University.Management.System.repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class InMemoryRepository<T> {
    private final Map<String, T> storage = new LinkedHashMap<>();
    private final AtomicInteger idGen = new AtomicInteger(1);

    protected abstract String getId(T entity);
    protected abstract void setId(T entity, String id);

    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<T> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    public T save(T entity) {
        String id = getId(entity);
        if (id == null || id.isBlank()) {
            id = String.valueOf(idGen.getAndIncrement());
            setId(entity, id);
        }
        storage.put(id, entity);
        return entity;
    }

    public void deleteById(String id) {
        storage.remove(id);
    }
}
