package com.citronix.dto.ferme;

import java.time.LocalDate;
import java.util.List;

import com.citronix.dto.champ.ChampResponseDto;

public record FermeResponseDto(
        Long id,
        String nom,
        String localisation,
        double superfecie,
        LocalDate dateCreation,
        List<ChampResponseDto> champs) {
}
