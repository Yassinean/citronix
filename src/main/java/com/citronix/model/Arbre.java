package com.citronix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.citronix.model.Champ;

import java.time.LocalDate;

@Entity
@Table(name = "arbre")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Arbre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate datePlantation;

    @NotNull
    @Positive
    private int age;

    @ManyToOne
    private Champ champ;
}
