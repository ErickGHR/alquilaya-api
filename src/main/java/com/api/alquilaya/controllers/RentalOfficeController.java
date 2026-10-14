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

import com.api.alquilaya.models.RentalOffice;
import com.api.alquilaya.services.RentalOfficeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rental-offices")
public class RentalOfficeController {

    private final RentalOfficeService service;

    public RentalOfficeController(RentalOfficeService service) { this.service = service; }

    @GetMapping
    public List<RentalOffice> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public RentalOffice findById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public ResponseEntity<RentalOffice> create(@Valid @RequestBody RentalOffice office) {
        office.setId(null);
        RentalOffice saved = service.save(office);
        return ResponseEntity.created(URI.create("/api/rental-offices/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public RentalOffice update(@PathVariable Long id, @Valid @RequestBody RentalOffice office) {
        service.findById(id);
        office.setId(id);
        return service.save(office);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
