package com.citronix.service.Implemenation;

import com.citronix.dto.recolte.RecolteRequestDto;
import com.citronix.dto.recolte.RecolteResponseDto;
import com.citronix.mapper.recolte.RecolteMapper;
import com.citronix.model.Champ;
import com.citronix.model.Recolte;
import com.citronix.repository.ChampRepository;
import com.citronix.repository.RecolteRepository;
import com.citronix.service.Interface.IRecolteService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class RecolteServiceImp implements IRecolteService {

    private final RecolteRepository recolteRepository;
    private final ChampRepository champRepository;
    private final RecolteMapper recolteMapper;

    @Override
    public RecolteResponseDto create(RecolteRequestDto recolteRequestDto) {
        Optional<Recolte> existingRecolte = recolteRepository.findByChampAndSaison(
                recolteRequestDto.champId(), recolteRequestDto.saison());
        if (existingRecolte.isPresent()) {
            throw new IllegalArgumentException("Ce champ a déjà une récolte pour cette saison !");
        }

        Recolte recolte = recolteMapper.toEntity(recolteRequestDto);
        recolte = recolteRepository.save(recolte);
        return recolteMapper.toResponseDto(recolte);
    }

    @Override
    public RecolteResponseDto update(Long id, RecolteRequestDto recolteRequestDto) {
        Recolte existingRecolte = recolteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Récolte non trouvée"));

        Champ existingChamp = champRepository.findById(recolteRequestDto.champId())
                .orElseThrow(() -> new RuntimeException("Récolte non trouvée"));

        existingRecolte.setDateDeRecolte(recolteRequestDto.dateDeRecolte());
        existingRecolte.setSaison(recolteRequestDto.saison());
        existingRecolte.setChamp(existingChamp);

        Recolte updatedRecolte = recolteRepository.save(existingRecolte);
        return recolteMapper.toResponseDto(updatedRecolte);
    }

    @Override
    public void delete(Long id) {
        recolteRepository.deleteById(id);
    }

    @Override
    public Optional<RecolteResponseDto> findById(Long id) {
        return recolteRepository.findById(id)
                .map(recolteMapper::toResponseDto);

    }

    @Override
    public List<RecolteResponseDto> findAll() {
        return recolteRepository.findAll().stream()
                .map(recolteMapper::toResponseDto)
                .toList();
    }

}
