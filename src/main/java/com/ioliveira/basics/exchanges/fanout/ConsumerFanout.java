package com.ioliveira.basics.exchanges.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerFanout {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("Message received: " + message);
        };

        channel.basicConsume("Fanout1", true, deliverCallback, consumerTag -> {});
        channel.basicConsume("Fanout2", true, deliverCallback, consumerTag -> {});
        channel.basicConsume("Fanout3", true, deliverCallback, consumerTag -> {});

    }
}
