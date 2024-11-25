package com.citronix.repository.Custom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.Predicate;
import com.citronix.dto.ferme.FermeSearchCriteria;
import com.citronix.model.Ferme;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Setter;

@Setter
@Component
public class FermeSpecification implements Specification<Ferme> {

    private FermeSearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Ferme> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.nom() != null) {
            predicates.add(cb.like(cb.lower(root.get("nom")), "%" + criteria.nom().toLowerCase() + "%"));
        }
        if (criteria.localisation() != null) {
            predicates.add(
                    cb.like(cb.lower(root.get("localisation")), "%" + criteria.localisation().toLowerCase() + "%"));
        }
        if (criteria.minSuperficie() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("superficie"), criteria.minSuperficie()));
        }
        if (criteria.maxSuperficie() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("superficie"), criteria.maxSuperficie()));
        }
        if (criteria.startDate() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("dateCreation"), criteria.startDate()));
        }
        if (criteria.endDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("dateCreation"), criteria.endDate()));
        }

        // Combine predicates
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
