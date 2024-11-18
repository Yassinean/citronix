package com.citronix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="champ")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Champ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nom;
    @Positive
    @NotNull
    private double superfecie;

    @ManyToOne
    @JoinColumn(name = "ferme_id" , nullable = false)
    private Ferme ferme;

    @OneToMany(mappedBy = "champ")
    private List<Arbre> arbres;
}
