package com.citronix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate dateVente;

    @NotNull
    @Positive
    private double prixUnitaire;

    @NotNull
    private String client;

    @NotNull
    @ManyToOne
    private Recolte recolte;
}
