package com.api.alquilaya.services;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.alquilaya.models.Customer;
import com.api.alquilaya.models.Rental;
import com.api.alquilaya.models.Rental.RentalStatus;
import com.api.alquilaya.models.Vehicle;
import com.api.alquilaya.models.Vehicle.VehicleStatus;
import com.api.alquilaya.repositories.RentalRepository;

@Service
public class RentalService extends BaseCrudService<Rental> {

    private final RentalRepository rentalRepository;
    private final VehicleService vehicleService;
    private final CustomerService customerService;

    public RentalService(
            RentalRepository rentalRepository,
            VehicleService vehicleService,
            CustomerService customerService) {
        super(rentalRepository, "El alquiler");
        this.rentalRepository = rentalRepository;
        this.vehicleService = vehicleService;
        this.customerService = customerService;
    }

    @Override
    @Transactional
    public Rental save(Rental rental) {
        Rental previous = rental.getId() == null ? null : findById(rental.getId());
        if (rental.getVehicle() == null || rental.getVehicle().getId() == null) {
            throw new IllegalArgumentException("Debes indicar el vehículo");
        }
        if (rental.getCustomer() == null || rental.getCustomer().getId() == null) {
            throw new IllegalArgumentException("Debes indicar el cliente");
        }
        if (rental.getRentalDate() == null || rental.getReturnDate() == null
                || rental.getReturnDate().isBefore(rental.getRentalDate())) {
            throw new IllegalArgumentException("Las fechas del alquiler no son válidas");
        }

        Vehicle vehicle = vehicleService.findById(rental.getVehicle().getId());
        Customer customer = customerService.findById(rental.getCustomer().getId());
        RentalStatus status = rental.getStatus() == null ? RentalStatus.PENDING : rental.getStatus();

        if (status != RentalStatus.COMPLETED) {
            if (vehicle.getStatus() == VehicleStatus.MAINTENANCE) {
                throw new IllegalArgumentException("El vehículo está en mantenimiento");
            }
            if (rentalRepository.hasOverlap(
                    vehicle.getId(), rental.getId(), rental.getRentalDate(), rental.getReturnDate())) {
                throw new IllegalArgumentException("El vehículo ya tiene un alquiler en esas fechas");
            }
        }

        long days = ChronoUnit.DAYS.between(rental.getRentalDate(), rental.getReturnDate()) + 1;
        rental.setTotalPrice(vehicle.getDailyRate().multiply(BigDecimal.valueOf(days)));
        rental.setVehicle(vehicle);
        rental.setCustomer(customer);
        rental.setStatus(status);

        if (previous != null
                && previous.getStatus() == RentalStatus.ACTIVE
                && (status != RentalStatus.ACTIVE
                    || !previous.getVehicle().getId().equals(vehicle.getId()))) {
            previous.getVehicle().updateStatus(VehicleStatus.AVAILABLE);
        }

        if (status == RentalStatus.ACTIVE) {
            vehicle.updateStatus(VehicleStatus.RENTED);
        } else if (status == RentalStatus.COMPLETED && vehicle.getStatus() == VehicleStatus.RENTED) {
            vehicle.updateStatus(VehicleStatus.AVAILABLE);
        }

        return rentalRepository.save(rental);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Rental rental = findById(id);
        if (rental.getStatus() == RentalStatus.ACTIVE
                && rental.getVehicle().getStatus() == VehicleStatus.RENTED) {
            rental.getVehicle().updateStatus(VehicleStatus.AVAILABLE);
        }
        rentalRepository.delete(rental);
    }
}
