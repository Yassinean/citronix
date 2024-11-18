package com.citronix.model;

import com.citronix.model.Enum.Saison;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @NotNull
    private double quantiteTotal;

    @NotNull
    private LocalDate dateRecolte;

    private Saison saisonStatus;
}
