package com.ioliveira.basics.exchanges.headers;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class PublishHeadersExchange {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //Headers Exchange encaminha a mensagem para as rotas definidas nos headers da mensagem
        //Importante: x-match = any (umas das consições devem estar presentes no header da mensagem = (OU) )
        //Importante: x-match = all (todas as consições devem estar presentes no header da mensagem = (AND) )

        Map<String, Object> headers = new HashMap<>();
        headers.put("item1", "mobile");
        headers.put("item2", "computer");

        channel.basicPublish("Headers", "", new AMQP.BasicProperties().builder().headers(headers).build(), "Message for Mobile, TV and Computer".getBytes());

        channel.close();
        connection.close();
    }
}
