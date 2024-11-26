package com.citronix.ChampTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.citronix.dto.champ.ChampRequestDto;
import com.citronix.dto.champ.ChampResponseDto;
import com.citronix.mapper.champ.ChampMapper;
import com.citronix.model.Champ;
import com.citronix.model.Ferme;
import com.citronix.repository.ChampRepository;
import com.citronix.repository.FermeRepository;
import com.citronix.service.Implemenation.ChampServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ChampServiceImpTest {

    @Mock
    private ChampRepository champRepository;

    @Mock
    private FermeRepository fermeRepository;

    @Mock
    private ChampMapper champMapper;

    @InjectMocks
    private ChampServiceImp champService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

     @Test
     void createChamp_ValidInput_ShouldReturnChampResponseDto() {
        Ferme ferme = Ferme.builder()
                .id(1L)
                .dateCreation(LocalDate.now())
                .localisation("Marrakech")
                .nom("test")
                .superfecie(30)
                .champs(new ArrayList<>())
                .build();
         ChampRequestDto requestDto = ChampRequestDto.builder()
                 .nom("Test Champ")
                 .superfecie(10.5)
                 .fermeId(ferme.getId())
                 .build();


         Champ champ = Champ.builder()
                 .nom("Test Champ")
                 .superfecie(10.5)
                 .ferme(ferme)
                 .build();

         when(fermeRepository.findById(ferme.getId())).thenReturn(Optional.of(ferme));
         when(champMapper.toEntity(requestDto)).thenReturn(champ);
         when(champRepository.save(champ)).thenReturn(champ);
         when(champMapper.toResponseDto(champ)).thenReturn(
                 ChampResponseDto.builder()
                         .id(1L)
                         .nom("Test Champ")
                         .superfecie(10.5)
                         .fermeNom("حديقة الحامض")
                         .build()
         );

         // Act
         ChampResponseDto responseDto = champService.create(requestDto);

         // Assert
         assertThat(responseDto).isNotNull();
         assertThat(responseDto.nom()).isEqualTo("Test Champ");
         assertThat(responseDto.fermeNom()).isEqualTo("حديقة الحامض");
         verify(fermeRepository, times(1)).findById(ferme.getId());
         verify(champRepository, times(1)).save(champ);
     }

     @Test
     void updateChamp_ValidId_ShouldUpdateAndReturnResponseDto() {
         Ferme ferme = Ferme.builder()
                 .id(1L)
                 .dateCreation(LocalDate.now())
                 .localisation("Marrakech")
                 .nom("test")
                 .superfecie(30)
                 .champs(new ArrayList<>())
                 .build();
         Long champId = 1L;
         ChampRequestDto requestDto = ChampRequestDto.builder()
                 .nom("Updated Champ")
                 .superfecie(15.0)
                 .fermeId(ferme.getId())
                 .build();

         Champ existingChamp = Champ
                 .builder()
                 .id(champId)
                 .nom("Updated Champ")
                 .build();
         when(fermeRepository.findById(ferme.getId())).thenReturn(Optional.of(ferme));
         when(champRepository.findById(champId)).thenReturn(Optional.of(existingChamp));
         when(champRepository.save(existingChamp)).thenReturn(existingChamp);
         when(champMapper.toResponseDto(existingChamp)).thenReturn(
                 ChampResponseDto.builder()
                         .id(champId)
                         .nom("Updated Champ")
                         .superfecie(15.0)
                         .fermeNom("Ferme 1")
                         .build());

         ChampResponseDto responseDto = champService.update(champId, requestDto);

         assertThat(responseDto.nom()).isEqualTo("Updated Champ");
         assertThat(responseDto.superfecie()).isEqualTo(15.0);
         verify(champRepository, times(1)).save(existingChamp);
     }

     @Test
     void deleteChamp_ValidId_ShouldDeleteChamp() {
         Long champId = 1L;
         doNothing().when(champRepository).deleteById(champId);

         champService.delete(champId);

         verify(champRepository, times(1)).deleteById(champId);
     }

     @Test
     void findChampById_ValidId_ShouldReturnResponseDto() {
         Long champId = 1L;
         Champ champ = Champ
                 .builder()
                 .id(champId)
                 .build();
         champ.setId(champId);

         when(champRepository.findById(champId)).thenReturn(Optional.of(champ));
         when(champMapper.toResponseDto(champ)).thenReturn(
                 ChampResponseDto.builder()
                         .id(champId)
                         .nom("Champ Name")
                         .superfecie(10.0)
                         .fermeNom("Ferme")
                         .build());

         Optional<ChampResponseDto> responseDto = champService.findById(champId);

         assertThat(responseDto).isPresent();
         assertThat(responseDto.get().nom()).isEqualTo("Champ Name");
     }

     @Test
     void findChampById_InvalidId_ShouldReturnEmpty() {
         Long champId = 1L;
         when(champRepository.findById(champId)).thenReturn(Optional.empty());

         Optional<ChampResponseDto> responseDto = champService.findById(champId);

         assertThat(responseDto).isEmpty();
     }

     @Test
     void findAllChamps_ShouldReturnListOfResponseDto() {
         Champ champ = Champ
                 .builder()
                 .id(1L)
                 .nom("Test Champ")
                 .build();
         champ.setId(1L);

         when(champRepository.findAll()).thenReturn(List.of(champ));
         when(champMapper.toResponseDto(champ)).thenReturn(
                 ChampResponseDto.builder()
                         .id(1L)
                         .nom("Test Champ")
                         .superfecie(10.0)
                         .fermeNom("Ferme")
                         .build());

         List<ChampResponseDto> responseDtos = champService.findAll();

         assertThat(responseDtos).isNotEmpty();
         assertThat(responseDtos.get(0).nom()).isEqualTo("Test Champ");
     }
}