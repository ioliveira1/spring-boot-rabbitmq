package com.ioliveira.basics.json;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerJson {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        JSONParser jsonParser = new JSONParser();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            try {
                Object parse = jsonParser.parse(message);
                JSONObject parse1 = (JSONObject) parse;
                Object id = parse1.get("id");
                Object name = parse1.get("name");
                Object email = parse1.get("email");

                System.out.println("Message received:" + message);
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        };

        channel.basicConsume("Fila-1", true, deliverCallback, consumerTag -> {});
    }
}
