package com.api.alquilaya.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 60)
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(nullable = false, length = 60)
    private String lastName;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido")
    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @NotBlank(message = "La licencia de conducir es obligatoria")
    @Column(nullable = false, unique = true, length = 40)
    private String driversLicense;

    @NotBlank(message = "El teléfono es obligatorio")
    @Column(nullable = false, length = 25)
    private String phone;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getDetails() {
        return getFullName() + " - " + driversLicense;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDriversLicense() { return driversLicense; }
    public void setDriversLicense(String driversLicense) { this.driversLicense = driversLicense; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
