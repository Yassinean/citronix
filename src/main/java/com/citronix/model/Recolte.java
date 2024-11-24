package com.citronix.model;

import com.citronix.model.Enum.Saison;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recolte")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recolte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dateDeRecolte;
    
    @Column(nullable = false)
    private double quantiteTotale;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Saison saison;

    @Transient
    private double revenu;

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;

    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecolteDetail> detailsRecolte;

    @OneToMany(mappedBy = "recolte" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Vente> ventes = new ArrayList<>();
}
