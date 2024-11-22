package com.citronix.dto.recolte;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RecolteDetailResponseDto(
        @NotNull Long arbreId,
        @Positive double quantite
) {
}
