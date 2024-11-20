package com.citronix.controller;

import com.citronix.dto.champ.ChampRequestDto;
import com.citronix.dto.champ.ChampResponseDto;
import com.citronix.service.Interface.IChampService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

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

     @PutMapping("/update/{id}")
    public ResponseEntity<ChampResponseDto> updateChamp(@PathVariable Long id,
            @Validated @RequestBody ChampRequestDto champRequestDto) {
        ChampResponseDto champResponseDto = champService.update(id, champRequestDto);
        if (champResponseDto == null) {
            return new ResponseEntity<>(champResponseDto, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(champResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ChampResponseDto> deleteChamp(@PathVariable Long id) {
        champService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampResponseDto> getChamp(@PathVariable Long id){
        Optional <ChampResponseDto> champ = champService.findById(id);
        return champ.map(ResponseEntity::ok).orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/list")
    public ResponseEntity<List<ChampResponseDto>> getAllChamps(){
        List <ChampResponseDto> champs = champService.findAll();
        return new ResponseEntity<>(champs,HttpStatus.OK);
    } 
}

