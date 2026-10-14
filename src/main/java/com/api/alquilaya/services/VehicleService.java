package com.api.alquilaya.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.alquilaya.models.RentalOffice;
import com.api.alquilaya.models.Vehicle;
import com.api.alquilaya.repositories.VehicleRepository;

@Service
public class VehicleService extends BaseCrudService<Vehicle> {

    private final VehicleRepository vehicleRepository;
    private final RentalOfficeService rentalOfficeService;

    public VehicleService(VehicleRepository vehicleRepository, RentalOfficeService rentalOfficeService) {
        super(vehicleRepository, "El vehículo");
        this.vehicleRepository = vehicleRepository;
        this.rentalOfficeService = rentalOfficeService;
    }

    @Override
    @Transactional
    public Vehicle save(Vehicle vehicle) {
        if (vehicle.getRentalOffice() == null || vehicle.getRentalOffice().getId() == null) {
            throw new IllegalArgumentException("Debes indicar la oficina del vehículo");
        }
        RentalOffice office = rentalOfficeService.findById(vehicle.getRentalOffice().getId());
        vehicle.setRentalOffice(office);

        vehicleRepository.findByLicensePlateIgnoreCase(vehicle.getLicensePlate())
                .filter(saved -> !saved.getId().equals(vehicle.getId()))
                .ifPresent(saved -> {
                    throw new IllegalArgumentException("Ya existe un vehículo con esa placa");
                });
        return vehicleRepository.save(vehicle);
    }
}
