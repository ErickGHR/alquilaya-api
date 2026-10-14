package com.api.alquilaya.poo;

public abstract class Vehiculo implements Alquilable {

    private final String matricula;
    private final double precioBaseDiario;
    private Cliente clienteActual;

    protected Vehiculo(String matricula, double precioBaseDiario) {
        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("La matrícula es obligatoria");
        }
        if (precioBaseDiario <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero");
        }
        this.matricula = matricula;
        this.precioBaseDiario = precioBaseDiario;
    }

    public abstract double calcularPrecioAlquiler(int dias);

    protected void validarDias(int dias) {
        if (dias <= 0) {
            throw new IllegalArgumentException("Los días deben ser mayores que cero");
        }
    }

    @Override
    public void alquilar(Cliente cliente) {
        if (!estaDisponible()) {
            throw new IllegalStateException("El vehículo ya está alquilado");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente es obligatorio");
        }
        clienteActual = cliente;
    }

    @Override
    public void devolver() {
        clienteActual = null;
    }

    @Override
    public boolean estaDisponible() {
        return clienteActual == null;
    }

    public String getMatricula() { return matricula; }
    public double getPrecioBaseDiario() { return precioBaseDiario; }
    public Cliente getClienteActual() { return clienteActual; }
    public String getTipo() { return getClass().getSimpleName(); }
}
