package com.api.alquilaya.poo;

public class Cliente {

    private final String dni;
    private String nombre;
    private String email;

    public Cliente(String dni, String nombre, String email) {
        if (dni == null || dni.length() != 9) {
            throw new IllegalArgumentException("El DNI debe tener exactamente 9 caracteres");
        }
        this.dni = dni;
        this.nombre = nombre;
        setEmail(email);
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El correo no es válido");
        }
        this.email = email;
    }
}
