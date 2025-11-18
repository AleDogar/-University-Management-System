package com.example.University.Management.System.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class InFileRepository<T> implements AbstractRepository<T> {

    private final ObjectMapper mapper;
    private final Class<T> entityType;
    private final Map<String, T> dataStore = new ConcurrentHashMap<>();
    private final String filePath;

    protected abstract String getId(T entity);
    protected abstract void setId(T entity, String id);

    public InFileRepository(ObjectMapper mapper,
                            @Value("${app.data-folder}") String dataFolder,
                            String fileName) {
        this.mapper = mapper;
        this.mapper.registerModule(new JavaTimeModule());

        if (!dataFolder.endsWith(File.separator)) {
            dataFolder += File.separator;
        }
        this.filePath = dataFolder + fileName;

        this.entityType = (Class<T>) ((ParameterizedType)
                getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @PostConstruct
    public void loadData() {
        dataStore.clear();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                mapper.writeValue(file, new ArrayList<T>());
                return;
            }

            List<T> list = Arrays.asList(
                    mapper.readValue(file,
                            mapper.getTypeFactory().constructArrayType(entityType))
            );

            for (T e : list) {
                String id = getId(e);
                if (id == null || id.isBlank()) {
                    id = UUID.randomUUID().toString();
                    setId(e, id);
                }
                dataStore.put(id, e);
            }
        } catch (Exception e) {
            throw new RuntimeException("Eroare la încărcarea datelor", e);
        }
    }

    private synchronized void saveToFile() {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            mapper.writeValue(file, dataStore.values());
        } catch (IOException e) {
            throw new RuntimeException("Eroare la scrierea datelor", e);
        }
    }

    @Override
    public synchronized T save(T entity) {
        String id = getId(entity);
        if (id == null || id.isBlank()) {
            id = UUID.randomUUID().toString();
            setId(entity, id);
        }
        dataStore.put(id, entity);
        saveToFile();
        return entity;
    }

    @Override
    public synchronized void delete(T entity) {
        dataStore.remove(getId(entity));
        saveToFile();
    }

    @Override
    public synchronized void deleteById(String id) {
        T entity = dataStore.get(id);
        if (entity != null) {
            dataStore.remove(id);
            saveToFile();
        }
    }

    @Override
    public T findById(String id) {
        return dataStore.get(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(dataStore.values());
    }
}
