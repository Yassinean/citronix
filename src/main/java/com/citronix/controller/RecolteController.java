package com.citronix.controller;

import com.citronix.dto.recolte.RecolteRequestDto;
import com.citronix.dto.recolte.RecolteResponseDto;
import com.citronix.service.Interface.IRecolteService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recoltes")
@AllArgsConstructor
public class RecolteController {

    private final IRecolteService recolteService;

    /**
     * Create a new Recolte
     */
    @PostMapping("/add")
    public ResponseEntity<RecolteResponseDto> createRecolte(
            @Valid @RequestBody RecolteRequestDto recolteRequestDto) {
        RecolteResponseDto responseDto = recolteService.create(recolteRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    /**
     * Update an existing Recolte by ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<RecolteResponseDto> updateRecolte(
            @PathVariable Long id,
            @Valid @RequestBody RecolteRequestDto recolteRequestDto) {
        RecolteResponseDto responseDto = recolteService.update(id, recolteRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Delete a Recolte by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        recolteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get a Recolte by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<RecolteResponseDto>> getRecolteById(@PathVariable Long id) {
        Optional<RecolteResponseDto> responseDto = recolteService.findById(id);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Get all Recoltes
     */
    @GetMapping("/list")
    public ResponseEntity<List<RecolteResponseDto>> getAllRecoltes() {
        List<RecolteResponseDto> responseDtos = recolteService.findAll();
        return ResponseEntity.ok(responseDtos);
    }
}
