package com.citronix.dto.champ;

import com.citronix.model.Ferme;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ChampRequestDto(
              @NotNull(message = "le nom du champ est obligatoire") String nom,
              @Positive @NotNull(message = "La surface doit etre superieur a zero") double superfecie,
              Ferme ferme) {
}