package com.example.teamcubation.model;


import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Data
public class Accion extends InstrumentoFinanciero {

    private double dividendo;

}
