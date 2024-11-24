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
        // Check for existing Recolte for the same Champ and Saison
        Optional<Recolte> existingRecolte = recolteRepository.findByChampAndSaison(
                recolteRequestDto.champId(), recolteRequestDto.saison());
        if (existingRecolte.isPresent()) {
            throw new IllegalArgumentException("Ce champ a déjà une récolte pour cette saison !");
        }

        // Get Champ and validate its Arbres
        Champ champ = getChampWithArbres(recolteRequestDto.champId());

        // Map RecolteRequestDto to Entity
        Recolte recolte = recolteMapper.toEntity(recolteRequestDto);
        recolte.setDateDeRecolte(recolteRequestDto.dateDeRecolte());
        recolte.setChamp(champ);

        // Save and map to ResponseDto
        recolte = recolteRepository.save(recolte);
        // Calculate quantiteTotal based on Arbres' productivité
        double quantiteTotal = champ.getArbres().stream()
                .mapToDouble(arbre -> arbre.getProductivite()) // Assuming `getProductivite()` exists in Arbre
                .sum();
        recolte.setQuantiteTotale(quantiteTotal);
        return recolteMapper.toResponseDto(recolte);
    }

    @Override
    public RecolteResponseDto update(Long id, RecolteRequestDto recolteRequestDto) {
        // Fetch existing Recolte and validate existence
        Recolte existingRecolte = recolteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Récolte non trouvée"));

        // Fetch Champ for update
        Champ existingChamp = champRepository.findById(recolteRequestDto.champId())
                .orElseThrow(() -> new RuntimeException("Champ non trouvé"));

        // Update Recolte details
        existingRecolte.setDateDeRecolte(recolteRequestDto.dateDeRecolte());
        existingRecolte.setSaison(recolteRequestDto.saison());
        existingRecolte.setChamp(existingChamp);

        // Recalculate quantiteTotal
        double quantiteTotal = existingChamp.getArbres().stream()
                .mapToDouble(arbre -> arbre.getProductivite())
                .sum();
        existingRecolte.setQuantiteTotale(quantiteTotal);

        // Save and return updated Recolte
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

    private Champ getChampWithArbres(Long champId) {
        Champ champ = champRepository.findById(champId)
                .orElseThrow(() -> new RuntimeException("Champ non trouvé"));

        // Ensure the Champ has associated Arbres
        if (champ.getArbres() == null || champ.getArbres().isEmpty()) {
            throw new RuntimeException("Le champ ne contient aucun arbre !");
        }

        return champ;
    }
}
