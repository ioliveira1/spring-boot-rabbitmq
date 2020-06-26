package com.ioliveira.springboot.controllers;

import com.ioliveira.springboot.entities.Person;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rabbitmq")
public class RabbitMQController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //Exemplo de envio diretamente para uma fila e para as exchanges do tipo direct, fanout e topic
    @GetMapping(path = "/{name}")
    public ResponseEntity<Person> sendMessage(@PathVariable("name") String name) {
        Person person = Person.builder().id(1L).name(name).build();

        //Mensagem direto para a queue Fila-1
        rabbitTemplate.convertAndSend("Fila-1", person.toString());

        //Mensagem para exchange Direct que redireciona para a queue Mobile
        rabbitTemplate.convertAndSend("Direct", "mobile", person.toString());

        //Mensagem para exchange Fanout (Broadcast)
        rabbitTemplate.convertAndSend("Fanout", "", person.toString());

        //Mensagem para exchange Topic que redireciona para a queue Mobile e Computer
        rabbitTemplate.convertAndSend("Topic", "tv.mobile.computer", person.toString());

        return ResponseEntity.ok(person);
    }

    //Exemplo de envio para a exchange do tipo headers
    @GetMapping(path = "/headers/{name}")
    public ResponseEntity<Person> sendMessageHeaders(@PathVariable("name") String name) {
        Person person = Person.builder().id(1L).name(name).build();

        Message message = MessageBuilder
                .withBody(person.toString().getBytes())
                .setHeader("item1", "mobile")
                .setHeader("item2", "television")
                .build();

        rabbitTemplate.send("Headers", "", message);

        return ResponseEntity.ok(person);
    }

}
