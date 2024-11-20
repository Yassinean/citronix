package com.citronix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "arbre")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Arbre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDate datePlantation;

    @NotNull
    @Positive
    private int age;

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    @ToString.Exclude
    private Champ champ;
}
