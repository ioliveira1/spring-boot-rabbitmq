package com.ioliveira.basics.exchanges.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PublishDirectExchange {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //Direct Exchange encaminha a mensagem somente para a fila com routing key cadastrado no binding
        channel.basicPublish("Direct", "mobile", null, "Mobile queue".getBytes());
        channel.basicPublish("Direct", "tv", null, "TV queue".getBytes());
        channel.basicPublish("Direct", "computer", null, "Computer queue".getBytes());

        channel.close();
        connection.close();
    }
}
