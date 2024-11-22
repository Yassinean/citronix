package com.citronix.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "recolteDetail")
@Data
@RequiredArgsConstructor
public class RecolteDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double quantite;

    @ManyToOne
    @JoinColumn(name = "recolte_id", nullable = false)
    private Recolte recolte;

    @ManyToOne
    @JoinColumn(name = "arbre_id", nullable = false)
    private Arbre arbre;
}
