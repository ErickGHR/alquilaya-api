package com.api.alquilaya.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.alquilaya.web.ResourceNotFoundException;

public abstract class BaseCrudService<T> {

    protected final JpaRepository<T, Long> repository;
    private final String resourceName;

    protected BaseCrudService(JpaRepository<T, Long> repository, String resourceName) {
        this.repository = repository;
        this.resourceName = resourceName;
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resourceName + " no encontrado con id " + id));
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
