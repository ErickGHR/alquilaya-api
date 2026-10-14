package com.api.alquilaya.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.api.alquilaya.models.Customer;
import com.api.alquilaya.models.Rental;
import com.api.alquilaya.models.Rental.RentalStatus;
import com.api.alquilaya.models.RentalOffice;
import com.api.alquilaya.models.Vehicle;
import com.api.alquilaya.models.Vehicle.VehicleStatus;

@SpringBootTest
@Transactional
class RentalServiceTests {

    @Autowired
    private RentalOfficeService rentalOfficeService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RentalService rentalService;

    @Test
    void calculaPrecioYCierraElAlquiler() {
        RentalOffice office = new RentalOffice();
        office.setOfficeName("Centro");
        office.setAddress("Avenida Principal 10");
        office.setCity("Ciudad de México");
        office.setRegion("Centro");
        office = rentalOfficeService.save(office);

        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Nissan");
        vehicle.setModel("Versa");
        vehicle.setYear(2025);
        vehicle.setLicensePlate("ABC-123");
        vehicle.setDailyRate(new BigDecimal("100.00"));
        vehicle.setRentalOffice(office);
        vehicle = vehicleService.save(vehicle);

        Customer customer = new Customer();
        customer.setFirstName("Ana");
        customer.setLastName("López");
        customer.setEmail("ana@correo.com");
        customer.setDriversLicense("LIC-001");
        customer.setPhone("5555555555");
        customer = customerService.save(customer);

        Rental rental = new Rental();
        rental.setVehicle(vehicle);
        rental.setCustomer(customer);
        rental.setRentalDate(LocalDate.of(2026, 10, 14));
        rental.setReturnDate(LocalDate.of(2026, 10, 16));
        rental.setStatus(RentalStatus.ACTIVE);
        rental = rentalService.save(rental);

        assertEquals(new BigDecimal("300.00"), rental.getTotalPrice());
        assertEquals(VehicleStatus.RENTED, vehicle.getStatus());

        Rental crossed = new Rental();
        crossed.setVehicle(vehicle);
        crossed.setCustomer(customer);
        crossed.setRentalDate(LocalDate.of(2026, 10, 15));
        crossed.setReturnDate(LocalDate.of(2026, 10, 18));
        assertThrows(IllegalArgumentException.class, () -> rentalService.save(crossed));

        rental.setStatus(RentalStatus.COMPLETED);
        rentalService.save(rental);
        assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
    }
}
