package com.eduardo.area.consumer;

import com.eduardo.area.DTOs.GeneratedRentalConfirmationConsumerDTO;
import com.eduardo.area.services.AreaService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class AreaConsumer {

    private final AreaService areaService;

    public AreaConsumer(AreaService areaService) {
        this.areaService = areaService;
    }

    @RabbitListener(queues = "${broker.queue.generated.rental.confirmation.name}")
    @SendTo
    public String listenGenerateRentQueue(@Payload GeneratedRentalConfirmationConsumerDTO generatedRentalConfirmationConsumerDTO) {
        try {
            areaService.rent(generatedRentalConfirmationConsumerDTO.areaId());
            return "Success";
        } catch (Exception e) {
            System.err.println("Erro ao processar mensagem de confirmação de aluguel: " + e.getMessage());
            throw new AmqpRejectAndDontRequeueException("Erro ao processar mensagem: " + e.getMessage(), e);
        }
    }
}
