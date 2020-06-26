package com.ioliveira.basics.exchanges.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PublishTopicExchange {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //Topic Exchange encaminha a mensagem para as rotas definidas na mensagem
        //Importante: * -> exatamente uma palavra / # -> 0 ou mais palavras
        channel.basicPublish("Topic", "tv.mobile.computer", null, "Message for Mobile and Computer".getBytes());
        channel.basicPublish("Topic", "mobile.tv.computer", null, "Message for TV and Computer".getBytes());
        channel.basicPublish("Topic", "computer", null, "Message for Computer".getBytes());

        channel.close();
        connection.close();
    }
}
