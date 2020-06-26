package com.ioliveira.basics.json;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PublisherJson {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("name", "Igor Oliveira");
        jsonObject.put("email", "igorfoliveira@gmail.com");

        channel.basicPublish("", "Fila-1", null, jsonObject.toString().getBytes());

        channel.close();
        connection.close();

    }
}
