package com.citronix.dto.ferme;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record FermeRequestDto(
        @NotNull(message = "Le nom est obligatoire") String nom,
        @NotNull(message = "La localisation est obligatoire") String localisation,
        @Positive(message = "La superficie doit être supérieure à zéro") @NotNull double superfecie,
        @NotNull(message = "La date de création est obligatoire") LocalDate dateCreation) {
}