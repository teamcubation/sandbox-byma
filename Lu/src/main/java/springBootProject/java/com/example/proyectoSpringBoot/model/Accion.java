package com.example.proyectoSpringBoot.model;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Entity
public class Accion extends InstrumentoFinanciero {
}
