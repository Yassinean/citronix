package com.citronix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vente")
@Data
@RequiredArgsConstructor
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
    @Positive
    private double quantite;
    
    @Transient
    private double revenu;

    @NotNull
    @ManyToOne
    @JoinColumn(name="recolte_id" , nullable = false)
    private Recolte recolte;

}
