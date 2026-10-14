package com.api.alquilaya.poo;

public class Bicicleta extends Vehiculo {

    private final boolean electrica;

    public Bicicleta(String matricula, double precioBaseDiario, boolean electrica) {
        super(matricula, precioBaseDiario);
        this.electrica = electrica;
    }

    @Override
    public double calcularPrecioAlquiler(int dias) {
        validarDias(dias);
        double extraElectrica = electrica ? 2.0 : 0.0;
        return (getPrecioBaseDiario() + extraElectrica) * dias;
    }

    public boolean isElectrica() { return electrica; }
}
