package com.citronix.mapper.champ;

import com.citronix.dto.champ.ChampRequestDto;
import com.citronix.dto.champ.ChampResponseDto;
import com.citronix.model.Champ;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChampMapper {
    Champ toEntity(ChampRequestDto champRequestDto);

    @Mapping(target = "nom", source = "champ.nom")
    @Mapping(target = "fermeNom", source = "ferme.nom")
    @Mapping(target = "localisation", source = "ferme.localisation")
    ChampResponseDto toResponseDto(Champ champ);
}
