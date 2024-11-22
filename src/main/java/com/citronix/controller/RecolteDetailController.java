package com.citronix.controller;

import com.citronix.dto.recolte.RecolteDetailRequestDto;
import com.citronix.dto.recolte.RecolteDetailResponseDto;
import com.citronix.service.Interface.IRecolteDetailService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recolteDetails")
@AllArgsConstructor
public class RecolteDetailController {

    private final IRecolteDetailService recolteDetailService;

    @PostMapping
    public ResponseEntity<RecolteDetailResponseDto> create(@RequestBody @Valid RecolteDetailRequestDto recolteDetailRequestDto) {
        RecolteDetailResponseDto response = recolteDetailService.create(recolteDetailRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecolteDetailResponseDto> findById(@PathVariable Long id) {
        return recolteDetailService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<RecolteDetailResponseDto>> findAll() {
        List<RecolteDetailResponseDto> response = recolteDetailService.findAll();
        return ResponseEntity.ok(response);
    }

}
