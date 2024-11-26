package com.citronix.model;

import jakarta.persistence.*;
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
    
    private String nom;
    private double superfecie;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ferme_id", nullable = false)
    private Ferme ferme;

    @OneToMany(mappedBy = "champ" , cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private List<Arbre> arbres;

    @OneToMany(mappedBy = "champ", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recolte> recoltes;

}
