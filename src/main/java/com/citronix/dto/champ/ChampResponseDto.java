package com.citronix.dto.champ;

import java.util.List;

import com.citronix.model.Arbre;

public record ChampResponseDto(
        Long id,
        String nom,
        double superfecie,
        List<Arbre> arbres) {
}
