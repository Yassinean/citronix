package com.citronix.mapper.recolte;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.citronix.dto.recolte.RecolteDetailRequestDto;
import com.citronix.dto.recolte.RecolteDetailResponseDto;
import com.citronix.model.RecolteDetail;

@Mapper(componentModel = "spring")
public interface RecolteDetailMapper {
    @Mapping(target = "arbre.id", source = "arbreId")
    RecolteDetail toEntity(RecolteDetailRequestDto recolteDetailRequestDto);
    
    @Mapping(target = "arbreId", source = "arbre.id")
    RecolteDetailResponseDto toResponseDto(RecolteDetail recolteDetail);
}
