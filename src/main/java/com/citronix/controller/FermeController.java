package com.citronix.controller;

import com.citronix.dto.ferme.FermeRequestDto;
import com.citronix.dto.ferme.FermeResponseDto;
import com.citronix.model.Ferme;
import com.citronix.service.Interface.IFermeService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/add")
    public ResponseEntity<FermeResponseDto> createFerme(@Validated @RequestBody FermeRequestDto fermeRequestDto) {
        FermeResponseDto fermeResponseDto = fermeService.create(fermeRequestDto);
        return new ResponseEntity<>(fermeResponseDto, HttpStatus.CREATED);
    }

   
    @PutMapping("/update/{id}")
    public ResponseEntity<FermeResponseDto> updateFerme(
            @PathVariable Long id,
            @Validated @RequestBody FermeRequestDto fermeRequestDto) {

        FermeResponseDto updatedFerme = fermeService.update(id, fermeRequestDto);
        if (updatedFerme == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedFerme, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFerme(@PathVariable Long id) {
        fermeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FermeResponseDto> getFermeById(@PathVariable Long id) {
        Optional<FermeResponseDto> fermeResponseDto = fermeService.findById(id);
        return fermeResponseDto.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/list")
    public ResponseEntity<List<FermeResponseDto>> getAllFermes() {
        List<FermeResponseDto> fermes = fermeService.findAll();
        return new ResponseEntity<>(fermes, HttpStatus.OK);
    }

     @GetMapping("/search")
    public ResponseEntity<List<Ferme>> searchFerme(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String localisation,
            @RequestParam(required = false) Double superficieMin,
            @RequestParam(required = false) Double superficieMax
    ) {
        List<Ferme> responseList = fermeService.searchFermes(name, localisation, superficieMin, superficieMax);
        return ResponseEntity.ok(responseList);
    }

}
