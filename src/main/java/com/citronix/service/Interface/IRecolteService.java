package com.citronix.service.Interface;

import com.citronix.dto.recolte.RecolteRequestDto;
import com.citronix.dto.recolte.RecolteResponseDto;

import java.util.List;
import java.util.Optional;

public interface IRecolteService {
    RecolteResponseDto create(RecolteRequestDto recolteRequestDto);
    RecolteResponseDto update(Long id , RecolteRequestDto recolteRequestDto);
    void delete(Long id);
    Optional<RecolteResponseDto> findById(Long id);
    List<RecolteResponseDto> findAll();
}
