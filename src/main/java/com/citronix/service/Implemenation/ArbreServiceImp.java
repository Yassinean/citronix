package com.citronix.service.Implemenation;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.citronix.dto.arbre.ArbreRequestDto;
import com.citronix.dto.arbre.ArbreResponseDto;
import com.citronix.exceptions.ArbreNotFoundException;
import com.citronix.exceptions.ChampNotFoundException;
import com.citronix.mapper.arbre.ArbreMapper;
import com.citronix.model.Arbre;
import com.citronix.model.Champ;
import com.citronix.repository.ArbreRepository;
import com.citronix.repository.ChampRepository;
import com.citronix.service.Interface.IArbreService;

@Service
@RequiredArgsConstructor
public class ArbreServiceImp implements IArbreService {

    private final ArbreMapper arbreMapper;
    private final ArbreRepository arbreRepository;
    private final ChampRepository champRepository;

    @Override
    public ArbreResponseDto create(ArbreRequestDto arbreRequestDto) {
        Champ champ = champRepository.findById(arbreRequestDto.champId())
                .orElseThrow(() -> new RuntimeException("Champ non trouvé"));

        validateArbreCount(champ);
        validatePlantingPeriod(arbreRequestDto);

        Arbre arbre = arbreMapper.toEntity(arbreRequestDto);
        arbre.setChamp(champ);
        arbre.updateDerivedFields();
        arbreRepository.save(arbre);

        return arbreMapper.toResponseDto(arbre);
    }

    @Override
    public ArbreResponseDto update(ArbreRequestDto arbreRequestDto, Long id) {
        Arbre arbre = arbreRepository.findById(id)
                .orElseThrow(() -> new ArbreNotFoundException("Arbre non trouvé"));

        Champ champ = champRepository.findById(arbreRequestDto.champId())
                .orElseThrow(() -> new ChampNotFoundException("Champ non trouvé"));

        validateArbreCount(champ);
        validatePlantingPeriod(arbreRequestDto);

        arbre.setDatePlantation(arbreRequestDto.datePlantation());
        arbre.setChamp(champ);
        arbre.updateDerivedFields();
        arbreRepository.save(arbre);

        return arbreMapper.toResponseDto(arbre);
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

    // === Constraint Validation Methods ===

    private void validateArbreCount(Champ champ) {
        double superfecie = champ.getSuperfecie();
        int maxTrees = (int) (superfecie * 1);
        if (champ.getArbres().size() >= maxTrees) {
            throw new IllegalArgumentException(
                    "Le nombre total d'arbres dans ce champ dépasse la densité maximale autorisée de 100 arbres par hectare.");
        }
    }

    private void validatePlantingPeriod(ArbreRequestDto arbre) {
        if (arbre.datePlantation().getMonthValue() < 3 || arbre.datePlantation().getMonthValue() > 5) {
            throw new IllegalArgumentException(
                    "Les arbres ne peuvent être plantés qu'entre les mois de mars et mai.");
        }
    }

}
