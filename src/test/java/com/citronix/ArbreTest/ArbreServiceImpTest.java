package com.citronix.ArbreTest;

import com.citronix.dto.arbre.ArbreRequestDto;
import com.citronix.dto.arbre.ArbreResponseDto;
import com.citronix.mapper.arbre.ArbreMapper;
import com.citronix.model.Arbre;
import com.citronix.model.Champ;
import com.citronix.repository.ArbreRepository;
import com.citronix.repository.ChampRepository;
import com.citronix.service.Implemenation.ArbreServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArbreServiceImpTest {
    @Mock
    private ArbreRepository arbreRepository;

    @Mock
    private ChampRepository champRepository;

    @Mock
    private ArbreMapper arbreMapper;

    @InjectMocks
    private ArbreServiceImp arbreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createArbre_ValidInput_ShouldReturnArbreResponseDto() {
        Long champId = 1L;
        Champ champ = Champ.builder()
                .id(champId)
                .nom("Test Champ")
                .superfecie(5.0)
                .arbres(List.of())
                .build();

        ArbreRequestDto requestDto = ArbreRequestDto.builder()
                .champId(champId)
                .datePlantation(LocalDate.of(2024, 4, 15))
                .build();

        Arbre arbre = Arbre.builder()
                .datePlantation(LocalDate.of(2024, 4, 15))
                .build();

        ArbreResponseDto responseDto = ArbreResponseDto.builder()
                .datePlantation(LocalDate.of(2024, 4, 15))
                .build();

        when(champRepository.findById(champId)).thenReturn(Optional.of(champ));
        when(arbreMapper.toEntity(requestDto)).thenReturn(arbre);
        when(arbreRepository.save(arbre)).thenReturn(arbre);
        when(arbreMapper.toResponseDto(arbre)).thenReturn(responseDto);

        ArbreResponseDto result = arbreService.create(requestDto);

        assertNotNull(result);
        assertEquals(LocalDate.of(2024, 4, 15), result.datePlantation());
        verify(arbreRepository).save(arbre);
    }

    @Test
    void createArbre_ChampNotFound_ShouldThrowException() {
        Long champId = 1L;
        ArbreRequestDto requestDto = ArbreRequestDto.builder()
                .champId(champId)
                .datePlantation(LocalDate.of(2024, 4, 15))
                .build();

        when(champRepository.findById(champId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> arbreService.create(requestDto));
    }

    @Test
    void createArbre_InvalidPlantingDate_ShouldThrowException() {
        Long champId = 1L;
        Champ champ = Champ.builder()
                .id(champId)
                .nom("Test Champ")
                .superfecie(5.0)
                .arbres(List.of())
                .build();

        ArbreRequestDto requestDto = ArbreRequestDto.builder()
                .champId(champId)
                .datePlantation(LocalDate.of(2024, 6, 15)) // Invalid date (June)
                .build();

        when(champRepository.findById(champId)).thenReturn(Optional.of(champ));

        assertThrows(IllegalArgumentException.class, () -> arbreService.create(requestDto));
    }

    @Test
    void updateArbre_ValidInput_ShouldReturnArbreResponseDto() {
        Long arbreId = 1L;
        Long champId = 2L;

        Arbre existingArbre = Arbre.builder()
                .id(arbreId)
                .datePlantation(LocalDate.of(2023, 4, 15))
                .build();

        Champ champ = Champ.builder()
                .id(champId)
                .nom("Updated Champ")
                .superfecie(10.0)
                .arbres(List.of(existingArbre))
                .build();

        ArbreRequestDto requestDto = ArbreRequestDto.builder()
                .champId(champId)
                .datePlantation(LocalDate.of(2024, 4, 20))
                .build();

        ArbreResponseDto responseDto = ArbreResponseDto.builder()
                .datePlantation(LocalDate.of(2024, 4, 20))
                .build();

        when(arbreRepository.findById(arbreId)).thenReturn(Optional.of(existingArbre));
        when(champRepository.findById(champId)).thenReturn(Optional.of(champ));
        when(arbreRepository.save(existingArbre)).thenReturn(existingArbre);
        when(arbreMapper.toResponseDto(existingArbre)).thenReturn(responseDto);

        ArbreResponseDto result = arbreService.update(requestDto, arbreId);

        assertNotNull(result);
        assertEquals(LocalDate.of(2024, 4, 20), result.datePlantation());
        verify(arbreRepository).save(existingArbre);
    }

    @Test
    void updateArbre_ArbreNotFound_ShouldThrowException() {
        Long arbreId = 1L;
        ArbreRequestDto requestDto = ArbreRequestDto.builder()
                .champId(2L)
                .datePlantation(LocalDate.of(2024, 4, 20))
                .build();

        when(arbreRepository.findById(arbreId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> arbreService.update(requestDto, arbreId));
    }

    @Test
    void deleteArbre_ValidId_ShouldDeleteArbre() {
        Long arbreId = 1L;

        arbreService.delete(arbreId);

        verify(arbreRepository, times(1)).deleteById(arbreId);
    }

    @Test
    void findById_ValidId_ShouldReturnArbreResponseDto() {
        Long arbreId = 1L;
        Arbre arbre = Arbre.builder()
                .id(arbreId)
                .datePlantation(LocalDate.of(2024, 4, 15))
                .build();

        ArbreResponseDto responseDto = ArbreResponseDto.builder()
                .datePlantation(LocalDate.of(2024, 4, 15))
                .build();

        when(arbreRepository.findById(arbreId)).thenReturn(Optional.of(arbre));
        when(arbreMapper.toResponseDto(arbre)).thenReturn(responseDto);

        Optional<ArbreResponseDto> result = arbreService.findById(arbreId);

        assertTrue(result.isPresent());
        verify(arbreRepository).findById(arbreId);
    }

    @Test
    void findAll_ShouldReturnListOfArbreResponseDto() {
        List<Arbre> arbres = List.of(
                Arbre.builder().id(1L).datePlantation(LocalDate.of(2024, 4, 15)).build(),
                Arbre.builder().id(2L).datePlantation(LocalDate.of(2024, 4, 20)).build()
        );

        List<ArbreResponseDto> responseDtos = List.of(
                ArbreResponseDto.builder().id(1L).datePlantation(LocalDate.of(2024, 4, 15)).build(),
                ArbreResponseDto.builder().id(2L).datePlantation(LocalDate.of(2024, 4, 20)).build()
        );

        when(arbreRepository.findAll()).thenReturn(arbres);
        when(arbreMapper.toResponseDto(any())).thenReturn(responseDtos.get(0), responseDtos.get(1));

        List<ArbreResponseDto> result = arbreService.findAll();

        assertEquals(2, result.size());
        verify(arbreRepository, times(1)).findAll();
    }

}

