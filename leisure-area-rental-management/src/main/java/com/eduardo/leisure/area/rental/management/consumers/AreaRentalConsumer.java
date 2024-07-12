package com.eduardo.leisure.area.rental.management.consumers;

import com.eduardo.leisure.area.rental.management.DTOs.GenerateRentalDTO;
import com.eduardo.leisure.area.rental.management.producers.AreaRentalProducer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class AreaRentalConsumer {

    private final AreaRentalProducer areaRentalProducer;


    public AreaRentalConsumer(AreaRentalProducer areaRentalProducer) {
        this.areaRentalProducer = areaRentalProducer;
    }

    @RabbitListener(queues = "${broker.queue.generate.rent.name}")
    public void listenGenerateRentQueue(@Payload GenerateRentalDTO generateRentalDTO) throws Exception {
        this.areaRentalProducer.publishMessageGeneratedRentalConfirmation(generateRentalDTO);
    }
}
