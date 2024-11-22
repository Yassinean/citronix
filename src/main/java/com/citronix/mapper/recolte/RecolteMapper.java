package com.citronix.mapper.recolte;

import com.citronix.mapper.champ.ChampMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.citronix.dto.recolte.RecolteRequestDto;
import com.citronix.dto.recolte.RecolteResponseDto;
import com.citronix.model.Recolte;

@Mapper(componentModel = "spring" , uses = {RecolteDetailMapper.class , ChampMapper.class})
public interface RecolteMapper {
    
    @Mapping(target = "champ.id", source = "champId")
    Recolte toEntity(RecolteRequestDto recolteRequestDto);
    
    @Mapping(target = "champ", source = "champ")
    @Mapping(target = "detailsRecolte", source = "detailsRecolte")
    RecolteResponseDto toResponseDto(Recolte recolte);
}
