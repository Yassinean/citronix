package com.citronix.repository.Custom;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.citronix.model.Ferme;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class FermeCustomImpl implements IFermeCustom{

    // @PersistenceContext
    // private EntityManager em;

    // @Override
    // public List<Ferme> searchFermes(String name, String localisation, Double superficieMin, Double superficieMax) {
    //     CriteriaBuilder cb = em.getCriteriaBuilder();
    //     CriteriaQuery<Ferme> query  = cb.createQuery(Ferme.class);
    //     Root<Ferme> root = query.from(Ferme.class);

    //     List<Predicate> predicates = new ArrayList<>();
    //     if (name != null && !name.isEmpty()) {
    //         predicates.add(cb.like(cb.lower(root.get("name")), "%" + name + "%"));
    //     }
    //     // Filtrer par localisation
    //     if (localisation != null && !localisation.isEmpty()) {
    //         predicates.add(cb.like(cb.lower(root.get("localisation")), "%" + localisation + "%"));
    //     }
    //     // Filtrer par superficie
    //     if (superficieMin != null) {
    //         predicates.add(cb.greaterThanOrEqualTo(root.get("superficie"), superficieMin));
    //     }
    //     if (superficieMax != null) {
    //         predicates.add(cb.lessThanOrEqualTo(root.get("superficie"), superficieMax));
    //     }
    //     query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
    //     return em.createQuery(query).getResultList();
    // }
}
