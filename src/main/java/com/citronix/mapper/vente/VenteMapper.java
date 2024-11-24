package com.citronix.mapper.vente;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.citronix.dto.vente.VenteRequestDto;
import com.citronix.dto.vente.VenteResponseDto;
import com.citronix.model.Vente;

@Mapper(componentModel = "spring")
public interface VenteMapper {

    @Mapping(target = "recolte.id", source = "recolteId")
    Vente toEntity(VenteRequestDto venteRequestDto);

    @Mapping(target = "recolteId", source = "recolte.id")
    VenteResponseDto toResponseDto(Vente vente);
}
