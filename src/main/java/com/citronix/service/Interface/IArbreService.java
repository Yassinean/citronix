package com.citronix.service.Interface;

import java.util.List;
import java.util.Optional;

import com.citronix.dto.arbre.ArbreRequestDto;
import com.citronix.dto.arbre.ArbreResponseDto;

public interface IArbreService {
    ArbreResponseDto create(ArbreRequestDto arbreRequestDto);
    ArbreResponseDto update(ArbreRequestDto arbreRequestDto , Long id);
    void delete(Long id);
    Optional<ArbreResponseDto> findById(Long id);
    List<ArbreResponseDto> findAll();
}
