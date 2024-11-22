package com.citronix.repository;

import com.citronix.model.Recolte;
import com.citronix.model.Enum.Saison;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecolteRepository extends JpaRepository<Recolte, Long> {
    @Query("SELECT r FROM Recolte r WHERE r.champ.id = :champId AND r.saison = :saison")
    Optional<Recolte> findByChampAndSaison(@Param("champId") Long champId, @Param("saison") Saison saison);

}
