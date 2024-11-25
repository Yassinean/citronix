package com.citronix.FermeTest;

import com.citronix.dto.ferme.FermeRequestDto;
import com.citronix.dto.ferme.FermeResponseDto;
import com.citronix.mapper.ferme.FermeMapper;
import com.citronix.model.Ferme;
import com.citronix.repository.FermeRepository;
import com.citronix.service.Implemenation.FermeServiceImp;

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

class FermeServiceImpTest {

    @Mock
    private FermeMapper fermeMapper;

    @Mock
    private FermeRepository fermeRepository;

    @InjectMocks
    private FermeServiceImp fermeServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateFerme() {
        FermeRequestDto requestDto = FermeRequestDto.builder()
                .nom("Farm1")
                .localisation("Location1")
                .superfecie(50.0)
                .dateCreation(LocalDate.now())
                .build();

        Ferme ferme = new Ferme();
        FermeResponseDto responseDto = FermeResponseDto.builder()
                .id(1L)
                .nom("Farm1")
                .localisation("Location1")
                .superfecie(50.0)
                .dateCreation(LocalDate.now())
                .build();

        when(fermeMapper.toEntity(requestDto)).thenReturn(ferme);
        when(fermeRepository.save(ferme)).thenReturn(ferme);
        when(fermeMapper.toResponseDto(ferme)).thenReturn(responseDto);

        FermeResponseDto result = fermeServiceImp.create(requestDto);

        assertNotNull(result);
        assertEquals("Farm1", result.nom());
        verify(fermeRepository, times(1)).save(ferme);
    }

    @Test
    void testUpdateFerme() {
        Long id = 1L;
        FermeRequestDto requestDto = FermeRequestDto.builder()
                .nom("UpdatedFarm")
                .localisation("UpdatedLocation")
                .superfecie(60.0)
                .dateCreation(LocalDate.now())
                .build();

        Ferme existingFerme = new Ferme();
        Ferme updatedFerme = new Ferme();
        FermeResponseDto responseDto = FermeResponseDto.builder()
                .id(1L)
                .nom("UpdatedFarm")
                .localisation("UpdatedLocation")
                .superfecie(60.0)
                .dateCreation(LocalDate.now())
                .build();

        when(fermeRepository.findById(id)).thenReturn(Optional.of(existingFerme));
        when(fermeRepository.save(existingFerme)).thenReturn(updatedFerme);
        when(fermeMapper.toResponseDto(updatedFerme)).thenReturn(responseDto);

        FermeResponseDto result = fermeServiceImp.update(id, requestDto);

        assertNotNull(result);
        assertEquals("UpdatedFarm", result.nom());
        verify(fermeRepository, times(1)).findById(id);
        verify(fermeRepository, times(1)).save(existingFerme);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Ferme ferme = new Ferme();
        FermeResponseDto responseDto = FermeResponseDto.builder()
                .id(1L)
                .nom("Farm1")
                .localisation("Location1")
                .superfecie(50.0)
                .dateCreation(LocalDate.now())
                .build();

        when(fermeRepository.findById(id)).thenReturn(Optional.of(ferme));
        when(fermeMapper.toResponseDto(ferme)).thenReturn(responseDto);

        Optional<FermeResponseDto> result = fermeServiceImp.findById(id);

        assertTrue(result.isPresent());
        assertEquals("Farm1", result.get().nom());
        verify(fermeRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteFerme() {
        Long id = 1L;

        doNothing().when(fermeRepository).deleteById(id);

        fermeServiceImp.delete(id);

        verify(fermeRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindAllFermes() {
        Ferme ferme = new Ferme();
        FermeResponseDto responseDto = FermeResponseDto.builder()
                .id(1L)
                .nom("Farm1")
                .localisation("Location1")
                .superfecie(50.0)
                .dateCreation(LocalDate.now())
                .build();

        when(fermeRepository.findAll()).thenReturn(List.of(ferme));
        when(fermeMapper.toResponseDto(ferme)).thenReturn(responseDto);

        List<FermeResponseDto> result = fermeServiceImp.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Farm1", result.get(0).nom());
        verify(fermeRepository, times(1)).findAll();
    }
}