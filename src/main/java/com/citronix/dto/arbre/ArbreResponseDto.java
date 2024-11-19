package com.citronix.dto.arbre;

import java.time.LocalDate;

import com.citronix.dto.champ.ChampSummaryDto;

public record ArbreResponseDto(
        LocalDate datePlantation,
        int age,
        ChampSummaryDto champ) {
}
