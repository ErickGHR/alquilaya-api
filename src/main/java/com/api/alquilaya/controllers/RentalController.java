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

import com.api.alquilaya.models.Rental;
import com.api.alquilaya.services.RentalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService service;

    public RentalController(RentalService service) { this.service = service; }

    @GetMapping
    public List<Rental> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Rental findById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public ResponseEntity<Rental> create(@Valid @RequestBody Rental rental) {
        rental.setId(null);
        Rental saved = service.save(rental);
        return ResponseEntity.created(URI.create("/api/rentals/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public Rental update(@PathVariable Long id, @Valid @RequestBody Rental rental) {
        service.findById(id);
        rental.setId(id);
        return service.save(rental);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
