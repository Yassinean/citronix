package com.citronix.dto.recolte;

import java.time.LocalDate;

import com.citronix.model.Enum.Saison;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record RecolteRequestDto(
        @NotNull(message = "La date de récolte est obligatoire")@FutureOrPresent LocalDate dateDeRecolte,
        @NotNull(message = "La saison est obligatoire") Saison saison,
        @NotNull(message = "L'ID du champ est obligatoire") Long champId
) {
}
