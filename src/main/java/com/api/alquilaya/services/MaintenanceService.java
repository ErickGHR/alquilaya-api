package com.api.alquilaya.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.alquilaya.models.Maintenance;
import com.api.alquilaya.models.Vehicle;
import com.api.alquilaya.repositories.MaintenanceRepository;

@Service
public class MaintenanceService extends BaseCrudService<Maintenance> {

    private final MaintenanceRepository maintenanceRepository;
    private final VehicleService vehicleService;

    public MaintenanceService(MaintenanceRepository maintenanceRepository, VehicleService vehicleService) {
        super(maintenanceRepository, "El mantenimiento");
        this.maintenanceRepository = maintenanceRepository;
        this.vehicleService = vehicleService;
    }

    @Override
    @Transactional
    public Maintenance save(Maintenance maintenance) {
        if (maintenance.getVehicle() == null || maintenance.getVehicle().getId() == null) {
            throw new IllegalArgumentException("Debes indicar el vehículo");
        }
        Vehicle vehicle = vehicleService.findById(maintenance.getVehicle().getId());
        maintenance.setVehicle(vehicle);
        return maintenanceRepository.save(maintenance);
    }
}
