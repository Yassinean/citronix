package com.citronix.controller;

import com.citronix.dto.champ.ChampRequestDto;
import com.citronix.dto.champ.ChampResponseDto;
import com.citronix.service.Interface.IChampService;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/champs")
@Validated
@AllArgsConstructor
public class ChampController {
    private final IChampService champService;

    @PostMapping("/add")
    public ResponseEntity<ChampResponseDto> createChamp(@Validated @RequestBody ChampRequestDto champRequestDto) {
        ChampResponseDto champResponseDto = champService.create(champRequestDto);
        return new ResponseEntity<>(champResponseDto,HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ChampResponseDto>> getAllChamps(){
        List <ChampResponseDto> champs = champService.findAll();
        return new ResponseEntity<>(champs,HttpStatus.OK);
    } 
}

