package com.example.proyectoSpringBoot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.example.proyectoSpringBoot.service.observer.Notificador;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class InstrumentoFinanciero extends Notificador{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nombre;
    @Column(nullable = false)
    private Double precio;
    @Column(nullable = false)
    private Integer tipo;
}