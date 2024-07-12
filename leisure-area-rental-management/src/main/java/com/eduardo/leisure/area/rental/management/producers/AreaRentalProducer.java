package com.eduardo.leisure.area.rental.management.producers;

import com.eduardo.leisure.area.rental.management.DTOs.GenerateRentalDTO;
import com.eduardo.leisure.area.rental.management.DTOs.GeneratedRentalConfirmationDTO;
import com.eduardo.leisure.area.rental.management.services.AreaRentalService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AreaRentalProducer {
    private final RabbitTemplate rabbitTemplate;

    private final AreaRentalService areaRentalService;

    public AreaRentalProducer(RabbitTemplate rabbitTemplate,
                              AreaRentalService areaRentalService) {
        this.rabbitTemplate = rabbitTemplate;
        this.areaRentalService = areaRentalService;
    }

    @Value("${broker.queue.generated.rental.confirmation.name}")
    private String routingKeyGeneratedRentalConfirmation;

    public void publishMessageGeneratedRentalConfirmation(GenerateRentalDTO generateRentalDTO) {
        try {
            GeneratedRentalConfirmationDTO generatedRentalConfirmationDTO = new GeneratedRentalConfirmationDTO();
            generatedRentalConfirmationDTO.setAreaId(generateRentalDTO.areaId());
            String response = (String) rabbitTemplate.convertSendAndReceive("", this.routingKeyGeneratedRentalConfirmation, generatedRentalConfirmationDTO);
            if (!"Success".equals(response)) {
                throw new AmqpRejectAndDontRequeueException("Failed to process rental confirmation");
            }
            this.areaRentalService.save(generateRentalDTO);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException("Failed to publish message to the queue", e);
        }
    }

}
