package com.citronix.mapper.ferme;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.citronix.dto.ferme.FermeRequestDto;
import com.citronix.dto.ferme.FermeResponseDto;
import com.citronix.mapper.champ.ChampMapper;
import com.citronix.model.Ferme;

@Mapper(componentModel = "spring", uses = ChampMapper.class)
public interface FermeMapper {
    Ferme toEntity(FermeRequestDto FermeRequestDto);
    @Mapping(target = "champs", source = "champs")
    FermeResponseDto toResponseDto(Ferme Ferme);
}
