package com.citronix.service.Interface;

import com.citronix.dto.ferme.FermeRequestDto;
import com.citronix.dto.ferme.FermeResponseDto;
import com.citronix.dto.ferme.FermeSearchCriteria;
import com.citronix.model.Ferme;

import java.util.List;
import java.util.Optional;

public interface IFermeService {
    FermeResponseDto create(FermeRequestDto champRequestDto);

    FermeResponseDto update(Long id, FermeRequestDto entity);

    void delete(Long id);

    Optional<FermeResponseDto> findById(Long id);

    List<FermeResponseDto> findAll();

    List<Ferme> searchFermes(FermeSearchCriteria criteria);
}
