package com.citronix.service.Implemenation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.citronix.dto.vente.VenteRequestDto;
import com.citronix.dto.vente.VenteResponseDto;
import com.citronix.mapper.vente.VenteMapper;
import com.citronix.model.Recolte;
import com.citronix.model.Vente;
import com.citronix.repository.RecolteRepository;
import com.citronix.repository.VenteRepository;
import com.citronix.service.Interface.IVenteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VenteServiceImp implements IVenteService {

    private final VenteRepository venteRepository;
    private final RecolteRepository recolteRepository;
    private final VenteMapper venteMapper;

     @Override
    public VenteResponseDto create(VenteRequestDto venteRequestDto) {
        Recolte recolte = recolteRepository.findById(venteRequestDto.recolteId())
            .orElseThrow(() -> new RuntimeException("Récolte non trouvée"));

        if (recolte.getQuantiteTotale() < venteRequestDto.quantite()) {
            throw new RuntimeException("Quantité insuffisante dans la récolte");
        }

        Vente vente = venteMapper.toEntity(venteRequestDto);
        vente.setDateVente(LocalDate.now());
        vente.setRevenu(vente.getQuantite() * vente.getPrixUnitaire());

        vente = venteRepository.save(vente);

        
        recolte.setQuantiteTotale(recolte.getQuantiteTotale() - venteRequestDto.quantite());
        recolteRepository.save(recolte);
        return venteMapper.toResponseDto(vente);
    }

    // @Override
    // public VenteResponseDto update(Long id, VenteRequestDto venteRequestDto) {
    //     Vente vente = venteRepository.findById(id)
    //         .orElseThrow(() -> new RuntimeException("Vente non trouvée"));

    //     Recolte recolte = recolteRepository.findById(venteRequestDto.recolteId())
    //         .orElseThrow(() -> new RuntimeException("Récolte non trouvée"));

    //     double deltaQuantite = venteRequestDto.quantite() - vente.getQuantite();
    //     if (deltaQuantite > 0 && recolte.getQuantiteTotale() < deltaQuantite) {
    //         throw new RuntimeException("Quantité insuffisante dans la récolte");
    //     }

    //     recolte.setQuantiteTotale(recolte.getQuantiteTotale() - deltaQuantite);
    //     recolteRepository.save(recolte);

    //     vente.setPrixUnitaire(venteRequestDto.prixUnitaire());
    //     vente.setQuantite(venteRequestDto.quantite());
    //     vente.setClient(venteRequestDto.client());
    //     vente.setRevenu(vente.getQuantite() * vente.getPrixUnitaire());
    //     vente.setRecolte(recolte);

    //     vente = venteRepository.save(vente);
    //     return venteMapper.toResponseDto(vente);
    // }

    @Override
    public void delete(Long id) {
        venteRepository.deleteById(id);
    }

    @Override
    public Optional<VenteResponseDto> findById(Long id) {
        return venteRepository.findById(id)
                .map(vente -> {
                    vente.setRevenu(vente.getQuantite() * vente.getPrixUnitaire());
                    return venteMapper.toResponseDto(vente);
                });
    }

    @Override
    public List<VenteResponseDto> findAll() {
        return venteRepository.findAll().stream()
                .map(vente -> {
                    vente.setRevenu(vente.getQuantite() * vente.getPrixUnitaire());
                    return venteMapper.toResponseDto(vente);
                })
                .toList();
    }

}
