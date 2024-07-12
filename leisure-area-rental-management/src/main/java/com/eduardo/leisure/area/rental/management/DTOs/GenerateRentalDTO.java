package com.eduardo.leisure.area.rental.management.DTOs;

import java.util.UUID;

public record GenerateRentalDTO(
        UUID userId,
        UUID areaId,
        String returnDate
) {
}
