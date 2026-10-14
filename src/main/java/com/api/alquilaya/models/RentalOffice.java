package com.api.alquilaya.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "rental_offices")
public class RentalOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la oficina es obligatorio")
    @Column(nullable = false, length = 100)
    private String officeName;

    @NotBlank(message = "La dirección es obligatoria")
    @Column(nullable = false, length = 180)
    private String address;

    @NotBlank(message = "La ciudad es obligatoria")
    @Column(nullable = false, length = 80)
    private String city;

    @NotBlank(message = "La región es obligatoria")
    @Column(nullable = false, length = 80)
    private String region;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getOfficeName() { return officeName; }
    public void setOfficeName(String officeName) { this.officeName = officeName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
}
