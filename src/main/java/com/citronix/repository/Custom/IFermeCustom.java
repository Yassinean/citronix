package com.citronix.repository.Custom;

import java.util.List;

import com.citronix.model.Ferme;

public interface IFermeCustom {
    List<Ferme> searchFermes(String name, String localisation, Double superficieMin, Double superficieMax);
}
