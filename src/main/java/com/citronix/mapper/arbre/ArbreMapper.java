package com.citronix.mapper.arbre;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.citronix.dto.arbre.ArbreRequestDto;
import com.citronix.dto.arbre.ArbreResponseDto;
import com.citronix.dto.champ.ChampSummaryDto;
import com.citronix.model.Arbre;
import com.citronix.model.Champ;

@Mapper(componentModel = "spring")
public interface ArbreMapper {
    Arbre toEntity(ArbreRequestDto arbreRequestDto);

    @Mapping(target = "age", expression = "java(arbre.getAge())")
    @Mapping(target = "productivite", expression = "java(arbre.getProductivite())")
    ArbreResponseDto toResponseDto(Arbre arbre);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nom", source = "nom")
    ChampSummaryDto toChampSummaryDto(Champ champ);
}
