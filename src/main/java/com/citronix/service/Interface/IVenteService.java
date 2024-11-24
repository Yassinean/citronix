package com.citronix.service.Interface;

import java.util.List;
import java.util.Optional;

import com.citronix.dto.vente.VenteRequestDto;
import com.citronix.dto.vente.VenteResponseDto;

public interface IVenteService {
    VenteResponseDto create(VenteRequestDto venteRequestDto);
    VenteResponseDto update(Long id , VenteRequestDto venteRequestDto);
    void delete(Long id);
    Optional<VenteResponseDto> findById(Long id);
    List<VenteResponseDto> findAll();
}
