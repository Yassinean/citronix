package com.citronix.dto.champ;

import lombok.Builder;

@Builder
public record ChampResponseDto(
        Long id,
        String nom,
        double superfecie,
        String fermeNom,
        String localisation) {
}
