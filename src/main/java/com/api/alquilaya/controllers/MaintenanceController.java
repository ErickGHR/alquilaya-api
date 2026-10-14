package com.api.alquilaya.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.alquilaya.models.Maintenance;
import com.api.alquilaya.services.MaintenanceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {

    private final MaintenanceService service;

    public MaintenanceController(MaintenanceService service) { this.service = service; }

    @GetMapping
    public List<Maintenance> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Maintenance findById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public ResponseEntity<Maintenance> create(@Valid @RequestBody Maintenance maintenance) {
        maintenance.setId(null);
        Maintenance saved = service.save(maintenance);
        return ResponseEntity.created(URI.create("/api/maintenances/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public Maintenance update(@PathVariable Long id, @Valid @RequestBody Maintenance maintenance) {
        service.findById(id);
        maintenance.setId(id);
        return service.save(maintenance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
