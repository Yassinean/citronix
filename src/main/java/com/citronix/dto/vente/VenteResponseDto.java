package com.citronix.dto.vente;

import java.time.LocalDate;

public record VenteResponseDto(
        Long id,
        LocalDate dateVente,
        double prixUnitaire,
        String client,
        double quantite,
        double revenu,
        Long recolteId
) {}
