package com.citronix.mapper.champ;

import com.citronix.dto.champ.ChampRequestDto;
import com.citronix.dto.champ.ChampResponseDto;
import com.citronix.model.Champ;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChampMapper {
    Champ toEntity(ChampRequestDto champRequestDto);
    ChampResponseDto toResponseDto(Champ champ);
}
