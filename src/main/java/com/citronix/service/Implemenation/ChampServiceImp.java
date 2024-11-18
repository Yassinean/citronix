package com.citronix.service.Implemenation;

import com.citronix.dto.champ.ChampRequestDto;
import com.citronix.dto.champ.ChampResponseDto;
import com.citronix.mapper.champ.ChampMapper;
import com.citronix.model.Champ;
import com.citronix.repository.ChampRepository;
import com.citronix.service.Interface.IChampService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChampServiceImp implements IChampService {
    private final ChampMapper champMapper;
    private final ChampRepository champRepository;

    @Override
    public ChampResponseDto create(ChampRequestDto champRequestDto) {
        Champ champ = champMapper.toEntity(champRequestDto);
        champRepository.save(champ);
        return champMapper.toResponseDto(champ);
    }

    @Override
    public ChampResponseDto update(Long id, ChampRequestDto champRequestDto) {
        Champ champ = champRepository.findById(id).orElseThrow(() -> new RuntimeException("Champ non trouve"));
        champ.setNom(champRequestDto.nom());
        champ.setSuperfecie(champRequestDto.superfecie());
        champ.setFerme(champRequestDto.ferme());
        Champ updatedChamp = champRepository.save(champ);
        return champMapper.toResponseDto(updatedChamp);
    }

    @Override
    public void delete(Long id) {
        champRepository.deleteById(id);
    }

    @Override
    public Optional<ChampResponseDto> findById(Long id) {
        return champRepository.findById(id).map(champMapper::toResponseDto);
    }

    @Override
    public List<ChampResponseDto> findAll() {
        return champRepository.findAll().stream().map(champMapper::toResponseDto).toList();
    }
}

