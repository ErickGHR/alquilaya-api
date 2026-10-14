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

import com.api.alquilaya.models.Vehicle;
import com.api.alquilaya.services.VehicleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) { this.service = service; }

    @GetMapping
    public List<Vehicle> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Vehicle findById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public ResponseEntity<Vehicle> create(@Valid @RequestBody Vehicle vehicle) {
        vehicle.setId(null);
        Vehicle saved = service.save(vehicle);
        return ResponseEntity.created(URI.create("/api/vehicles/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public Vehicle update(@PathVariable Long id, @Valid @RequestBody Vehicle vehicle) {
        service.findById(id);
        vehicle.setId(id);
        return service.save(vehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
