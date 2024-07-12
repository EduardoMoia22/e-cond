package com.eduardo.leisure.area.rental.management.configs;

import com.eduardo.leisure.area.rental.management.entities.RentalStatus;
import com.eduardo.leisure.area.rental.management.repositories.RentalStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final RentalStatusRepository rentalStatusRepository;

    public DataLoader(RentalStatusRepository rentalStatusRepository) {
        this.rentalStatusRepository = rentalStatusRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(RentalStatus.Values.values())
                .map(RentalStatus.Values::toRentalStatus)
                .forEach(this.rentalStatusRepository::save);
    }
}
