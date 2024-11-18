package com.citronix.service.Implemenation;

import com.citronix.dto.ferme.FermeRequestDto;
import com.citronix.dto.ferme.FermeResponseDto;
import com.citronix.mapper.ferme.FermeMapper;
import com.citronix.model.Ferme;
import com.citronix.repository.FermeRepository;
import com.citronix.service.Interface.IFermeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FermeServiceImp implements IFermeService {
    private final FermeMapper fermeMapper;
    private final FermeRepository fermeRepository;

    @Override
    public FermeResponseDto create(FermeRequestDto fermeRequestDto) {
        Ferme ferme = fermeMapper.toEntity(fermeRequestDto);
        fermeRepository.save(ferme);
        return fermeMapper.toResponseDto(ferme);
    }

    @Override
    public FermeResponseDto update(Long id, FermeRequestDto fermeRequestDto) {
        Ferme ferme = fermeRepository.findById(id).orElseThrow(() -> new RuntimeException("Ferme non trouvée"));

        ferme.setNom(fermeRequestDto.nom());
        ferme.setLocalisation(fermeRequestDto.localisation());
        ferme.setSuperfecie(fermeRequestDto.superfecie());
        ferme.setDateCreation(fermeRequestDto.dateCreation());
    
        Ferme updatedFerme = fermeRepository.save(ferme);

        return fermeMapper.toResponseDto(updatedFerme);
    }

    @Override
    public void delete(Long id) {
        fermeRepository.deleteById(id);
    }

    @Override
    public Optional<FermeResponseDto> findById(Long id) {
        return fermeRepository.findById(id).map(fermeMapper::toResponseDto);
    }

    @Override
    public List<FermeResponseDto> findAll() {
        return fermeRepository.findAll().stream().map(fermeMapper::toResponseDto).toList();
    }
}
