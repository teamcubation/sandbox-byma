package com.example.project.model;

import com.example.project.model.instrumentofinanciero.InstrumentoFinanciero;
import com.example.project.service.observer.Observer;

import java.time.LocalDate;

public class Inversor implements Observer {
    private String nombre;
    private LocalDate fechaDeNacimiento;
    private boolean esSuscriptor;

    public Inversor(String nombre, LocalDate fechaDeNacimiento) {
        this.nombre = nombre;
        this.esSuscriptor = false;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean esSuscriptor() {
        return esSuscriptor;
    }

    public boolean isEsSuscriptor() {
        return esSuscriptor;
    }

    public void setEsSuscriptor(boolean esSuscriptor) {
        this.esSuscriptor = esSuscriptor;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    @Override
    public void update(InstrumentoFinanciero instrumentoFinanciero, String atributo) {
        System.out.println("Se actualizo el atributo " + atributo + " del instrumentoFinanciero " + instrumentoFinanciero.toString() +
                "\n Soy inversor " + this.getNombre() + " y fui notificado.");
    }

    public String toString() {
        return "Inversor{nombre= " + this.getNombre() + ", fechaDeNacimiento= " + getFechaDeNacimiento().toString() + ", esSuscriptor= " + esSuscriptor + "}";
    }
}
