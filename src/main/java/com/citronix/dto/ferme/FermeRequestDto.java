package com.citronix.dto.ferme;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FermeRequestDto(
        @NotNull(message = "le nom du champ est obligatoire") String nom,
        @NotNull(message = "la localisation du champs est obligatoire") String localisation,
        @Positive @NotNull(message = "La surface doit etre superieur a zero") double superfecie,
        @NotNull(message = "La date est obligatoire") LocalDate dateCreation) {
}