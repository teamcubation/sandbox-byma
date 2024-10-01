package com.example.proyectoSpringBoot.model;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
public class Bono extends InstrumentoFinanciero {
}
