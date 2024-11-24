package com.citronix.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citronix.dto.vente.*;
import com.citronix.service.Interface.IVenteService;

import jakarta.validation.Valid;

@RequestMapping("/api/ventes")
@RestController

public class VenteController {
    private IVenteService venteService;

    public VenteController(IVenteService venteService) {
        this.venteService = venteService;
    }

    @PostMapping("/add")
    public ResponseEntity<VenteResponseDto> create(@Valid @RequestBody VenteRequestDto venteRequestDto) {
        VenteResponseDto vente = venteService.create(venteRequestDto);
        return new ResponseEntity<>(vente, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VenteResponseDto> update(@Valid @RequestBody @PathVariable Long id,
            VenteRequestDto venteRequestDto) {
        VenteResponseDto vente = venteService.update(id, venteRequestDto);
        if (vente == null) {
            return new ResponseEntity<>(vente, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vente, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<VenteResponseDto> delete(@PathVariable Long id) {
        venteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenteResponseDto> getVente(@PathVariable Long id) {
        Optional<VenteResponseDto> vente = venteService.findById(id);
        return vente.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/list")
    public ResponseEntity<List<VenteResponseDto>> getAll() {
        List<VenteResponseDto> ventes = venteService.findAll();
        return new ResponseEntity<>(ventes, HttpStatus.OK);
    }

}
