package com.api.alquilaya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.alquilaya.models.Maintenance;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
}
