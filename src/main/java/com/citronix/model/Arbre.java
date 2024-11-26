package com.citronix.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

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
    private LocalDate datePlantation;
    private int age;
    private double productivite;

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    @ToString.Exclude
    private Champ champ;

    public void calculateAge() {
        if (datePlantation != null) {
            this.age = Period.between(datePlantation, LocalDate.now()).getYears();
        } else {
            this.age = 0;
        }
    }

    public void calculateProductivite() {
        if (age < 3) {
            this.productivite = 2.5;
        } else if (age <= 10) {
            this.productivite = 12.0;
        } else {
            this.productivite = 20.0;
        }
    }

    public void updateDerivedFields() {
        calculateAge();
        calculateProductivite();
    }

}