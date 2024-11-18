package com.citronix.service.Interface;

import com.citronix.dto.champ.ChampRequestDto;
import com.citronix.dto.champ.ChampResponseDto;

import java.util.List;
import java.util.Optional;

public interface IChampService {
    ChampResponseDto create(ChampRequestDto champRequestDto);
    ChampResponseDto update(Long id , ChampRequestDto entity);
    void delete(Long id);
    Optional<ChampResponseDto> findById(Long id);
    List<ChampResponseDto> findAll();
}