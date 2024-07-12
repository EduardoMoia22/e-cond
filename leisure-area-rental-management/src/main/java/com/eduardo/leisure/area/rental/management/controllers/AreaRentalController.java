package com.eduardo.leisure.area.rental.management.controllers;

import com.eduardo.leisure.area.rental.management.DTOs.GenerateRentalDTO;
import com.eduardo.leisure.area.rental.management.producers.AreaRentalProducer;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/area-rental")
public class AreaRentalController {
    private final AreaRentalProducer areaRentalProducer;

    public AreaRentalController(AreaRentalProducer areaRentalProducer) {
        this.areaRentalProducer = areaRentalProducer;
    }

    @PostMapping
    public ResponseEntity<?> generateAreaRental(@RequestBody GenerateRentalDTO generateRentalDTO) {
        try {
            this.areaRentalProducer.publishMessageGeneratedRentalConfirmation(generateRentalDTO);
            return ResponseEntity.ok().build();
        } catch (AmqpRejectAndDontRequeueException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar mensagem de confirmação de aluguel: " + e.getMessage());
        }
    }
}
