package com.citronix.mapper.ferme;

import org.mapstruct.Mapper;

import com.citronix.dto.ferme.FermeRequestDto;
import com.citronix.dto.ferme.FermeResponseDto;
import com.citronix.model.Ferme;

@Mapper(componentModel = "spring")
public interface FermeMapper {
    Ferme toEntity(FermeRequestDto FermeRequestDto);

    FermeResponseDto toResponseDto(Ferme Ferme);
}
