package com.citronix.dto.champ;

public record ChampResponseDto(
        Long id,
        String nom,
        double superfecie,
        String fermeNom,
        String localisation) {
}
