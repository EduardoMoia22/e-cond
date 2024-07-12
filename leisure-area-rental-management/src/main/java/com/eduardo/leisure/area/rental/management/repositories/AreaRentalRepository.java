package com.eduardo.leisure.area.rental.management.repositories;

import com.eduardo.leisure.area.rental.management.entities.AreaRental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AreaRentalRepository extends JpaRepository<AreaRental, UUID> {
}
