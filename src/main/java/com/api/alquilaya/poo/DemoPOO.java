package com.api.alquilaya.poo;

public final class DemoPOO {

    private DemoPOO() {
    }

    public static void main(String[] args) {
        Flota flota = new Flota();
        flota.agregar(new Bicicleta("BICI-01", 10, false));
        flota.agregar(new Bicicleta("BICI-02", 10, true));
        flota.mostrarPresupuestos(3).forEach(System.out::println);

        try {
            // Mando un DNI corto para que entre al catch.
            new Cliente("123", "Ana", "ana@correo.com");
        } catch (IllegalArgumentException exception) {
            System.out.println("Error controlado: " + exception.getMessage());
        }
    }
}
