package com.eduardo.leisure.area.rental.management.repositories;

import com.eduardo.leisure.area.rental.management.entities.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalStatusRepository extends JpaRepository<RentalStatus, Long> {
}
