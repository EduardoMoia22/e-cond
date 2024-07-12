package com.eduardo.area.services;

import com.eduardo.area.DTOs.AreaRequestDTO;
import com.eduardo.area.entities.Area;
import com.eduardo.area.repositories.AreaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AreaService {

    private final AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public Area save(AreaRequestDTO areaRequestDTO) {
        Area area = new Area();
        BeanUtils.copyProperties(areaRequestDTO, area);
        area.setAvailable(true);
        return this.areaRepository.save(area);
    }

    public Optional<Area> findById(UUID id) {
        return this.areaRepository.findById(id);
    }

    public List<Area> findAll() {
        return this.areaRepository.findAll();
    }

    public Boolean rent(UUID id) throws IllegalStateException{
        Optional<Area> area = this.findById(id);

        if (area.isPresent()) {
            area.get().rent();
            this.areaRepository.save(area.get());
            return true;
        }

        return false;
    }

    public Boolean giveBack(UUID id){
        Optional<Area> area = this.findById(id);

        if (area.isPresent()) {
            area.get().giveBack();
            this.areaRepository.save(area.get());
            return true;
        }
        return false;
    }
}
