package com.citronix.dto.recolte;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RecolteDetailRequestDto(
        @NotNull(message = "L'ID de l'arbre est obligatoire") Long arbreId,
        @Positive(message = "La quantité récoltée doit être supérieure à zéro") double quantite) {
}