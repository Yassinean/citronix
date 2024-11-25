package com.citronix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "champ")
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
    @Positive(message = "La superficie doit être un nombre positif.")
    @DecimalMin(value = "0.1", message = "La superficie minimale du champ est de 0,1 hectare")
    @NotNull
    private double superfecie;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ferme_id", nullable = false)
    private Ferme ferme;

    @OneToMany(mappedBy = "champ")
    @JsonIgnore
    @ToString.Exclude
    private List<Arbre> arbres;

    @OneToMany(mappedBy = "champ", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recolte> recoltes;

    @Override
    public String toString() {
        return "Champ{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", superfecie=" + superfecie +
                '}';
    }
}
