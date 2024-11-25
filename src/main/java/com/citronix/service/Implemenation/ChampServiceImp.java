package com.citronix.service.Implemenation;

import com.citronix.dto.champ.ChampRequestDto;
import com.citronix.dto.champ.ChampResponseDto;
import com.citronix.mapper.champ.ChampMapper;
import com.citronix.model.Champ;
import com.citronix.model.Ferme;
import com.citronix.repository.ChampRepository;
import com.citronix.repository.FermeRepository;
import com.citronix.service.Interface.IChampService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ChampServiceImp implements IChampService {
    private final ChampMapper champMapper;
    private final ChampRepository champRepository;
    private final FermeRepository fermeRepository;

    @Override
    public ChampResponseDto create(ChampRequestDto champRequestDto) {
        Ferme ferme = fermeRepository.findById(champRequestDto.fermeId())
                .orElseThrow(() -> new RuntimeException("Ferme non trouvée"));

        validateChampSuperficie(champRequestDto.superfecie(), ferme);
        validateNumberOfChamps(ferme);
        Champ champ = champMapper.toEntity(champRequestDto);
        champ.setFerme(ferme);
        champRepository.save(champ);

        return champMapper.toResponseDto(champ);
    }

    @Override
    public ChampResponseDto update(Long id, ChampRequestDto champRequestDto) {
        Champ existingChamp = champRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Champ non trouvé"));

        Ferme ferme = fermeRepository.findById(champRequestDto.fermeId())
                .orElseThrow(() -> new RuntimeException("Ferme non trouvée"));

        double newSuperficie = champRequestDto.superfecie();

        double currentTotal = ferme.getChamps().stream()
                .filter(champ -> !champ.getId().equals(existingChamp.getId())) // Exclude the current Champ
                .mapToDouble(Champ::getSuperfecie)
                .sum();

        double updatedTotalSuperficie = currentTotal + newSuperficie;
        if (updatedTotalSuperficie > ferme.getSuperfecie()) {
            throw new IllegalArgumentException(
                    "La somme des superficies des champs ne peut pas dépasser la superficie totale de la ferme.");
        }
        validateChampSuperficie(newSuperficie, ferme);

        existingChamp.setNom(champRequestDto.nom());
        existingChamp.setSuperfecie(newSuperficie);
        existingChamp.setFerme(ferme);

        Champ updatedChamp = champRepository.save(existingChamp);

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

    // === Constraint Validation Methods ===

    private void validateChampSuperficie(double superfecie, Ferme ferme) {
    
        if (superfecie < 0.1) {
            throw new IllegalArgumentException("La superficie d'un champ doit être au minimum de 0.1 hectare.");
        }
        if (superfecie > 0.5 * ferme.getSuperfecie()) {
            throw new IllegalArgumentException(
                    "La superficie d'un champ ne peut pas dépasser 50% de la superficie totale de la ferme.");
        }

        double totalSuperficie = ferme.getChamps().stream()
                .mapToDouble(Champ::getSuperfecie)
                .sum() + superfecie;

        if (totalSuperficie > ferme.getSuperfecie()) {
            throw new IllegalArgumentException(
                    "La somme des superficies des champs ne peut pas dépasser la superficie totale de la ferme.");
        }
    }

    private void validateNumberOfChamps(Ferme ferme) {
        if (ferme.getChamps() != null && ferme.getChamps().size() >= 10) {
            throw new IllegalArgumentException("Une ferme ne peut contenir plus de 10 champs.");
        }

    }
}
