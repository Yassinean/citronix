package com.citronix.service.Interface;

import com.citronix.dto.recolte.RecolteDetailRequestDto;
import com.citronix.dto.recolte.RecolteDetailResponseDto;

import java.util.List;
import java.util.Optional;

public interface IRecolteDetailService {
    RecolteDetailResponseDto create(RecolteDetailRequestDto recolteDetailRequestDto);

    List<RecolteDetailResponseDto> findAll();

    Optional<RecolteDetailResponseDto> findById(Long id);
}
