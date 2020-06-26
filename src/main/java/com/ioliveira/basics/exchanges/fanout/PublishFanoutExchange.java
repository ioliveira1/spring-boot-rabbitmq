package com.ioliveira.basics.exchanges.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PublishFanoutExchange {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //Fanout Exchange encaminha a mensagem para todas as filas cadastradas (broadcasting)
        channel.basicPublish("Fanout", "", null, "Fanout queues".getBytes());

        channel.close();
        connection.close();
    }
}
