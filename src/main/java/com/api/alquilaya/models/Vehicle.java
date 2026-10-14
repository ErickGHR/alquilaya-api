package com.api.alquilaya.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La marca es obligatoria")
    @Column(nullable = false, length = 60)
    private String make;

    @NotBlank(message = "El modelo es obligatorio")
    @Column(nullable = false, length = 60)
    private String model;

    @NotNull(message = "El año es obligatorio")
    @Min(value = 1900, message = "El año no es válido")
    @Column(name = "model_year", nullable = false)
    private Integer year;

    @NotBlank(message = "La placa es obligatoria")
    @Column(nullable = false, unique = true, length = 20)
    private String licensePlate;

    @NotNull(message = "La tarifa diaria es obligatoria")
    @Positive(message = "La tarifa diaria debe ser mayor que cero")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal dailyRate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private VehicleStatus status = VehicleStatus.AVAILABLE;

    @NotNull(message = "La oficina es obligatoria")
    @ManyToOne(optional = false)
    @JoinColumn(name = "rental_office_id", nullable = false)
    private RentalOffice rentalOffice;

    @PrePersist
    void beforeCreate() {
        if (status == null) {
            status = VehicleStatus.AVAILABLE;
        }
    }

    public String getDetails() {
        return year + " " + make + " " + model + " - " + licensePlate;
    }

    public void updateStatus(VehicleStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("El estado del vehículo es obligatorio");
        }
        status = newStatus;
    }

    public enum VehicleStatus {
        AVAILABLE, RENTED, MAINTENANCE
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public BigDecimal getDailyRate() { return dailyRate; }
    public void setDailyRate(BigDecimal dailyRate) { this.dailyRate = dailyRate; }
    public VehicleStatus getStatus() { return status; }
    public void setStatus(VehicleStatus status) { this.status = status; }
    public RentalOffice getRentalOffice() { return rentalOffice; }
    public void setRentalOffice(RentalOffice rentalOffice) { this.rentalOffice = rentalOffice; }
}
