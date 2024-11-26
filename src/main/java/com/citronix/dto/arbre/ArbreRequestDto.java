package com.citronix.dto.arbre;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ArbreRequestDto(
                @NotNull LocalDate datePlantation,
                @NotNull Long champId) {
}
