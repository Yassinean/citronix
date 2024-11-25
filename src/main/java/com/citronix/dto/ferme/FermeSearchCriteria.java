package com.citronix.dto.ferme;

import java.time.LocalDate;

public record FermeSearchCriteria(
        String nom,
        String localisation,
        Double minSuperficie,
        Double maxSuperficie,
        LocalDate startDate,
        LocalDate endDate) {
}
