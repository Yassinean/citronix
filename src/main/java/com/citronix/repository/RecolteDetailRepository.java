package com.citronix.repository;

import com.citronix.model.RecolteDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecolteDetailRepository extends JpaRepository<RecolteDetail, Long> {
    @Query("""
            SELECT rd
            FROM RecolteDetail rd
            JOIN rd.recolte r
            WHERE rd.arbre.id = :arbreId AND r.saison = :saison
            """)
    Optional<RecolteDetail> findByArbreAndSaison(@Param("arbreId") Long arbreId, @Param("saison") String saison);

}
