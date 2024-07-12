package com.eduardo.area.repositories;

import com.eduardo.area.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AreaRepository extends JpaRepository<Area, UUID> {
}
