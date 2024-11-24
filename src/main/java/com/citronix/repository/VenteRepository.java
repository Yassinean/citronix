package com.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citronix.model.Vente;

public interface VenteRepository extends JpaRepository<Vente,Long>{

}
