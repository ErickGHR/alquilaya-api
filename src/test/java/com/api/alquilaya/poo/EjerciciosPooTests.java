package com.api.alquilaya.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class EjerciciosPooTests {

    @Test
    void bicicletaElectricaSumaDosEurosPorDia() {
        Bicicleta bicicleta = new Bicicleta("BICI-01", 10, true);
        assertEquals(36.0, bicicleta.calcularPrecioAlquiler(3));
    }

    @Test
    void dniIncorrectoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cliente("123", "Ana", "ana@correo.com"));
    }

    @Test
    void flotaAceptaLaNuevaSubclaseSinCambiarSuLogica() {
        Flota flota = new Flota();
        flota.agregar(new Bicicleta("BICI-02", 8, false));
        assertEquals("Bicicleta BICI-02 -> 16.00", flota.mostrarPresupuestos(2).getFirst());
    }
}
