package com.api.alquilaya.poo;

public interface Alquilable {
    void alquilar(Cliente cliente);
    void devolver();
    boolean estaDisponible();
}
