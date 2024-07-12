package com.eduardo.area.controllers;

import com.eduardo.area.DTOs.AreaRequestDTO;
import com.eduardo.area.entities.Area;
import com.eduardo.area.services.AreaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/areas")
public class AreaController {

    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @PostMapping
    public ResponseEntity<Area> save(@RequestBody AreaRequestDTO areaRequestDTO){
        return ResponseEntity.ok(this.areaService.save(areaRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<Area>> findAll(){
        return ResponseEntity.ok(this.areaService.findAll());
    }

    @GetMapping("/{areaId}")
    public ResponseEntity<Area> findById(@PathVariable("areaId") UUID areaId){
        Optional<Area> area = this.areaService.findById(areaId);

        if(area.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(area.get());
    }

    @PutMapping("/rent/{areaId}")
    public ResponseEntity<String> rent(@PathVariable("areaId") UUID areaId) throws IllegalStateException{
        try {
            this.areaService.rent(areaId);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/give-back/{areaId}")
    public ResponseEntity<Void> giveBack(@PathVariable("areaId") UUID areaId){
        this.areaService.giveBack(areaId);

        return ResponseEntity.noContent().build();
    }
}
