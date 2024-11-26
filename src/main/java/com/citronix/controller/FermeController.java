package com.citronix.controller;

import com.citronix.dto.ferme.FermeRequestDto;
import com.citronix.dto.ferme.FermeResponseDto;
import com.citronix.dto.ferme.FermeSearchCriteria;
import com.citronix.mapper.ferme.FermeMapper;
import com.citronix.model.Ferme;
import com.citronix.service.Interface.IFermeService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fermes")
@Validated
@AllArgsConstructor

public class FermeController {
    private final IFermeService fermeService;
    private final FermeMapper fermeMapper;

    @PostMapping("/add")
    public ResponseEntity<FermeResponseDto> createFerme(@Validated @RequestBody FermeRequestDto fermeRequestDto) {
        FermeResponseDto fermeResponseDto = fermeService.create(fermeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(fermeResponseDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FermeResponseDto> updateFerme(
            @PathVariable Long id,
            @Validated @RequestBody FermeRequestDto fermeRequestDto) {
        FermeResponseDto updatedFerme = fermeService.update(id, fermeRequestDto);
        return ResponseEntity.ok(updatedFerme);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFerme(@PathVariable Long id) {
        fermeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FermeResponseDto> getFermeById(@PathVariable Long id) {
        return fermeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/list")
    public ResponseEntity<List<FermeResponseDto>> getAllFermes() {
        List<FermeResponseDto> fermes = fermeService.findAll();
        return ResponseEntity.ok(fermes);
    }

     @PostMapping("/search")
    public ResponseEntity<List<FermeResponseDto>> searchFermes(@RequestBody FermeSearchCriteria criteria) {
        List<Ferme> fermes = fermeService.searchFermes(criteria);
        return ResponseEntity.ok(fermes.stream()
                .map(fermeMapper::toResponseDto)
                .collect(Collectors.toList()));
    }

}
