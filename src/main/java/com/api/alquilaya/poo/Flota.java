package com.api.alquilaya.poo;

import java.util.ArrayList;
import java.util.List;

public class Flota {

    private final List<Vehiculo> vehiculos = new ArrayList<>();

    public void agregar(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    public List<String> mostrarPresupuestos(int dias) {
        return vehiculos.stream()
                .map(vehiculo -> "%s %s -> %.2f".formatted(
                        vehiculo.getTipo(),
                        vehiculo.getMatricula(),
                        vehiculo.calcularPrecioAlquiler(dias)))
                .toList();
    }

    public List<Vehiculo> getVehiculos() {
        return List.copyOf(vehiculos);
    }
}
