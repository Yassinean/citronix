package com.citronix.repository;

import com.citronix.model.RecolteDetail;
import com.citronix.model.Enum.Saison;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecolteDetailRepository extends JpaRepository<RecolteDetail, Long> {
    // Check if an arbre already has a recolte for a specific saison
    @Query("""
                SELECT COUNT(rd) > 0
                FROM RecolteDetail rd
                WHERE rd.arbre.id = :arbreId
                  AND rd.recolte.saison = :saison
            """)
    boolean existsByArbreAndSaison(@Param("arbreId") Long arbreId, @Param("saison") Saison saison);

}
