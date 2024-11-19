package com.citronix.dto.arbre;

import java.time.LocalDate;

import com.citronix.model.Champ;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ArbreRequestDto(
        @NotNull LocalDate datePlantation,
        @NotNull @Positive int age,
        Champ champ) {
}
