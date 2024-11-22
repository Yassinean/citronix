package com.citronix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ferme")
public class Ferme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nom;
    @NotNull
    private String localisation;
    @NotNull
    @Positive
    private double superfecie;
    @NotNull
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "ferme", fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    private List<Champ> champs;

    @Override
    public String toString() {
        return "Ferme{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", localisation='" + localisation + '\'' +
                ", superfecie=" + superfecie +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
