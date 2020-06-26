package com.ioliveira.springboot.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    //Consumer para as filas {"Fanout1", "Fanout2", "Fanout3"}
    @RabbitListener(queues = {"Mobile", "TV"})
    public void getMessage(String person) {
        System.out.println(person);
    }

}
