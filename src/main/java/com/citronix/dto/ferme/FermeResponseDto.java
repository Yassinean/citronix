package com.citronix.dto.ferme;

import java.time.LocalDate;

public record FermeResponseDto(
        Long id,
        String nom,
        String localisation,
        double superfecie,
        LocalDate dateCreation) {
}
