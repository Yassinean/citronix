package com.citronix.dto.arbre;

import java.time.LocalDate;

import com.citronix.dto.champ.ChampSummaryDto;
import lombok.Builder;

@Builder
public record ArbreResponseDto(
        Long id,
        LocalDate datePlantation,
        int age,
        double productivite,
        ChampSummaryDto champ) {
}
