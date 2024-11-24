package com.citronix.dto.vente;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record VenteRequestDto(
        @NotNull(message = "Prix unitaire est obligatoire") @Positive double prixUnitaire,
        @NotNull(message = "Client est obligatoire") String client,
        @NotNull(message = "Quantité est obligatoire") @Positive double quantite,
        @NotNull(message = "Récolte est obligatoire") Long recolteId
) {}
