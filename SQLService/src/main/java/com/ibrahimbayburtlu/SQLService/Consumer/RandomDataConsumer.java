package com.ibrahimbayburtlu.SQLService.Consumer;

import com.ibrahimbayburtlu.SQLService.Entity.RandomData;
import com.ibrahimbayburtlu.SQLService.repository.RandomDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class RandomDataConsumer {

    private final RandomDataRepository randomDataRepository;

    public RandomDataConsumer(RandomDataRepository randomDataRepository) {
        this.randomDataRepository = randomDataRepository;
    }

    @RabbitListener(queues = "${data.queue.name}")
    public void handleMessage(Message message) {
        try {
            String rawData = new String(message.getBody(), StandardCharsets.UTF_8);
            log.info("Received raw data: {}", rawData);

            processRawData(rawData);

        } catch (NumberFormatException e) {
            log.error("Error parsing integer value", e);
        } catch (Exception e) {
            log.error("Error processing message", e);
        }
    }

    private void processRawData(String rawData) {
        String[] parts = rawData.split(",");

        if (parts.length != 3) {
            log.error("Invalid data format received: {}", rawData);
            return;
        }

        String timestamp = parts[0].trim();
        Integer randomInt = Integer.parseInt(parts[1].trim());
        String hashValue = parts[2].trim();

        saveRandomData(timestamp, randomInt, hashValue);
    }

    private void saveRandomData(String timestamp, Integer randomInt, String hashValue) {
        RandomData randomData = new RandomData();
        randomData.setTimestamp(timestamp);
        randomData.setRandomInteger(randomInt);
        randomData.setMd5HCharacters(hashValue);

        randomDataRepository.save(randomData);
        log.info("Data saved successfully: {}", randomData);
    }
}
