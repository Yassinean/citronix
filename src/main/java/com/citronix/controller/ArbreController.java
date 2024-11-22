package com.citronix.controller;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citronix.dto.arbre.ArbreRequestDto;
import com.citronix.dto.arbre.ArbreResponseDto;
import com.citronix.service.Interface.IArbreService;


@RestController
@RequestMapping("/api/arbres")
@AllArgsConstructor
public class ArbreController {

    private final IArbreService arbreService;

    @PostMapping("/add")
    public ResponseEntity<ArbreResponseDto> createArbre(@Validated @RequestBody ArbreRequestDto arbreRequestDto) {
        ArbreResponseDto arbre = arbreService.create(arbreRequestDto);
        return new ResponseEntity<>(arbre, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArbreResponseDto> updateArbre(@PathVariable Long id,
            @Validated @RequestBody ArbreRequestDto arbreRequestDto) {
        ArbreResponseDto arbreResponseDto = arbreService.update(arbreRequestDto, id);
        if (arbreResponseDto == null) {
            return new ResponseEntity<>(arbreResponseDto, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(arbreResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ArbreResponseDto> deleteArbre(@PathVariable Long id) {
        arbreService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArbreResponseDto> getArbre(@PathVariable Long id){
        Optional <ArbreResponseDto> arbre = arbreService.findById(id);
        return arbre.map(ResponseEntity::ok).orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ArbreResponseDto>> getAllArbres() {
        List<ArbreResponseDto> arbres = arbreService.findAll();
        return new ResponseEntity<>(arbres, HttpStatus.OK);
    }
}
