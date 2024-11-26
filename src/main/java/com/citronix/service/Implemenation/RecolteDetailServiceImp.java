package com.citronix.service.Implemenation;

import com.citronix.dto.recolte.RecolteDetailRequestDto;
import com.citronix.dto.recolte.RecolteDetailResponseDto;
import com.citronix.exceptions.ArbreNotFoundException;
import com.citronix.exceptions.RecolteNotFoundException;
import com.citronix.mapper.recolte.RecolteDetailMapper;
import com.citronix.model.Arbre;
import com.citronix.model.Recolte;
import com.citronix.model.RecolteDetail;
import com.citronix.repository.ArbreRepository;
import com.citronix.repository.RecolteDetailRepository;
import com.citronix.repository.RecolteRepository;
import com.citronix.service.Interface.IRecolteDetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional

public class RecolteDetailServiceImp implements IRecolteDetailService {

    private final RecolteDetailRepository recolteDetailRepository;
    private final RecolteRepository recolteRepository;
    private final ArbreRepository arbreRepository;
    private final RecolteDetailMapper recolteDetailMapper;

    @Override
    public RecolteDetailResponseDto create(RecolteDetailRequestDto recolteDetailRequestDto) {
        Recolte recolte = recolteRepository.findById(recolteDetailRequestDto.recolteId())
            .orElseThrow(() -> new RecolteNotFoundException("Recolte non trouvée"));

        Arbre arbre = arbreRepository.findById(recolteDetailRequestDto.arbreId())
            .orElseThrow(() -> new ArbreNotFoundException("Arbre non trouvé"));

        if (recolteDetailRepository.existsByArbreAndSaison(arbre.getId(), recolte.getSaison())) {
            throw new RuntimeException("Cet arbre est déjà récolté pour cette saison");
        }

        RecolteDetail recolteDetail = recolteDetailMapper.toEntity(recolteDetailRequestDto);
        recolteDetail.setRecolte(recolte);
        recolteDetail.setArbre(arbre);

        recolteDetail = recolteDetailRepository.save(recolteDetail);

        return recolteDetailMapper.toResponseDto(recolteDetail);
    }

    @Override
    public List<RecolteDetailResponseDto> findAll() {
        return recolteDetailRepository.findAll()
            .stream()
            .map(recolteDetailMapper::toResponseDto)
            .toList();
    }

    @Override
    public Optional<RecolteDetailResponseDto> findById(Long id) {
        return recolteDetailRepository.findById(id)
            .map(recolteDetailMapper::toResponseDto);
    }
}
