package com.citronix.dto.recolte;

import com.citronix.dto.champ.ChampResponseDto;

import java.time.LocalDate;
import java.util.List;

public record RecolteResponseDto(
        Long id,
        LocalDate dateDeRecolte,
        double quantiteTotale,
        String saison,
        ChampResponseDto champ,
        List<RecolteDetailResponseDto> detailsRecolte) {
}
