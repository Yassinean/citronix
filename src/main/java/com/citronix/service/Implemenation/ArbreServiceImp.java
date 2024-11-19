package com.citronix.service.Implemenation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.citronix.dto.arbre.ArbreRequestDto;
import com.citronix.dto.arbre.ArbreResponseDto;
import com.citronix.mapper.arbre.ArbreMapper;
import com.citronix.model.Arbre;
import com.citronix.repository.ArbreRepository;
import com.citronix.service.Interface.IArbreService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArbreServiceImp implements IArbreService{

    private final ArbreMapper arbreMapper;
    private final ArbreRepository arbreRepository;

    @Override
    public ArbreResponseDto create(ArbreRequestDto arbreRequestDto) {
        Arbre arbre = arbreMapper.toEntity(arbreRequestDto);
        arbreRepository.save(arbre);
        return arbreMapper.toResponseDto(arbre);
    }

    @Override
    public ArbreResponseDto update(ArbreRequestDto arbreRequestDto, Long id) {
        Arbre arbre = arbreRepository.findById(id).orElseThrow(()-> new RuntimeException("Arbre non trouve"));
        arbre.setDatePlantation(arbreRequestDto.datePlantation());
        arbre.setAge(arbreRequestDto.age());
        arbre.setChamp(arbreRequestDto.champ());

        Arbre updatedArbre = arbreRepository.save(arbre);
        return arbreMapper.toResponseDto(updatedArbre);
    }

    @Override
    public void delete(Long id) {
        arbreRepository.deleteById(id);
    }

    @Override
    public Optional<ArbreResponseDto> findById(Long id) {
        return arbreRepository.findById(id).map(arbreMapper::toResponseDto);
    }

    @Override
    public List<ArbreResponseDto> findAll() {
        return arbreRepository.findAll().stream().map(arbreMapper::toResponseDto).toList();
    }

}
