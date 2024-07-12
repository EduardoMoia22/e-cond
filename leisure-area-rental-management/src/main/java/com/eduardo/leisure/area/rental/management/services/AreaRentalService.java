package com.eduardo.leisure.area.rental.management.services;

import com.eduardo.leisure.area.rental.management.DTOs.GenerateRentalDTO;
import com.eduardo.leisure.area.rental.management.entities.AreaRental;
import com.eduardo.leisure.area.rental.management.entities.RentalStatus;
import com.eduardo.leisure.area.rental.management.repositories.AreaRentalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AreaRentalService {

    private final AreaRentalRepository areaRentalRepository;

    public AreaRentalService(AreaRentalRepository areaRentalRepository) {
        this.areaRentalRepository = areaRentalRepository;
    }

    public AreaRental save(GenerateRentalDTO generateRentalDTO) {
        AreaRental areaRental = new AreaRental();
        areaRental.setAreaId(generateRentalDTO.areaId());
        areaRental.setStatus(RentalStatus.Values.RENTED.toRentalStatus());
        areaRental.setUserId(generateRentalDTO.userId());
        areaRental.setReturnDate(LocalDateTime.parse(generateRentalDTO.returnDate()));
        areaRental.setRentalDate(LocalDateTime.now());

        return this.areaRentalRepository.save(areaRental);
    }
}
